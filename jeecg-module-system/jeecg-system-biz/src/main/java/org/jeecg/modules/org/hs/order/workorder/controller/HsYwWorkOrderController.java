package org.jeecg.modules.org.hs.order.workorder.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.modules.org.hs.order.workorder.entity.HsYwWorkOrder;
import org.jeecg.modules.org.hs.order.workorder.service.IHsYwWorkOrderService;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 工单主表
 * @Author: jeecg-boot
 * @Date:   2022-11-11
 * @Version: V1.0
 */
@Api(tags="工单主表")
@RestController
@RequestMapping("/org.hs.order/hsYwWorkOrder")
@Slf4j
public class HsYwWorkOrderController extends JeecgController<HsYwWorkOrder, IHsYwWorkOrderService> {
	@Autowired
	private IHsYwWorkOrderService hsYwWorkOrderService;

	/**
	 * 分页列表查询
	 *
	 * @param hsYwWorkOrder
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "工单主表-分页列表查询")
	@ApiOperation(value="工单主表-分页列表查询", notes="工单主表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<HsYwWorkOrder>> queryPageList(HsYwWorkOrder hsYwWorkOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<HsYwWorkOrder> queryWrapper = QueryGenerator.initQueryWrapper(hsYwWorkOrder, req.getParameterMap());
		Page<HsYwWorkOrder> page = new Page<HsYwWorkOrder>(pageNo, pageSize);
		IPage<HsYwWorkOrder> pageList = hsYwWorkOrderService.page(page, queryWrapper);
		return Result.OK(pageList);
	}

	/**
	 *   添加
	 *
	 * @param hsYwWorkOrder
	 * @return
	 */
	@AutoLog(value = "工单主表-添加")
	@ApiOperation(value="工单主表-添加", notes="工单主表-添加")
	//@RequiresPermissions("org.jeecg.modules.demo:hs_yw_work_order:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody HsYwWorkOrder hsYwWorkOrder) {
		hsYwWorkOrderService.save(hsYwWorkOrder);
		return Result.OK("添加成功！");
	}

	/**
	 *  编辑
	 *
	 * @param hsYwWorkOrder
	 * @return
	 */
	@AutoLog(value = "工单主表-编辑")
	@ApiOperation(value="工单主表-编辑", notes="工单主表-编辑")
	//@RequiresPermissions("org.jeecg.modules.demo:hs_yw_work_order:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody HsYwWorkOrder hsYwWorkOrder) {
		hsYwWorkOrderService.updateById(hsYwWorkOrder);
		return Result.OK("编辑成功!");
	}

	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "工单主表-通过id删除")
	@ApiOperation(value="工单主表-通过id删除", notes="工单主表-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.demo:hs_yw_work_order:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		hsYwWorkOrderService.removeById(id);
		return Result.OK("删除成功!");
	}

	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "工单主表-批量删除")
	@ApiOperation(value="工单主表-批量删除", notes="工单主表-批量删除")
	//@RequiresPermissions("org.jeecg.modules.demo:hs_yw_work_order:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.hsYwWorkOrderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}

	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "工单主表-通过id查询")
	@ApiOperation(value="工单主表-通过id查询", notes="工单主表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<HsYwWorkOrder> queryById(@RequestParam(name="id",required=true) String id) {
		HsYwWorkOrder hsYwWorkOrder = hsYwWorkOrderService.getById(id);
		if(hsYwWorkOrder==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(hsYwWorkOrder);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param hsYwWorkOrder
    */
    //@RequiresPermissions("org.jeecg.modules.demo:hs_yw_work_order:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, HsYwWorkOrder hsYwWorkOrder) {
        return super.exportXls(request, hsYwWorkOrder, HsYwWorkOrder.class, "工单主表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("hs_yw_work_order:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, HsYwWorkOrder.class);
    }

}
