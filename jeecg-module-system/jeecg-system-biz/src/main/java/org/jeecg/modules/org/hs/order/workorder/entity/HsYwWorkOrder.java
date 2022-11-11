package org.jeecg.modules.org.hs.order.workorder.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: 工单主表
 * @Author: jeecg-boot
 * @Date:   2022-11-11
 * @Version: V1.0
 */
@Data
@TableName("hs_yw_work_order")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="hs_yw_work_order对象", description="工单主表")
public class HsYwWorkOrder implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**创建人*/
    @ApiModelProperty(value = "创建人")
    private String createBy;
	/**创建日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private Date createTime;
	/**更新人*/
    @ApiModelProperty(value = "更新人")
    private String updateBy;
	/**更新日期*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private Date updateTime;
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private String sysOrgCode;
	/**工单编码*/
	@Excel(name = "工单编码", width = 15)
    @ApiModelProperty(value = "工单编码")
    private String number;
	/**工单状态*/
	@Excel(name = "工单状态", width = 15, dicCode = "work_order_statu")
	@Dict(dicCode = "work_order_statu")
    @ApiModelProperty(value = "工单状态")
    private String status;
	/**受理人*/
	@Excel(name = "受理人", width = 15, dictTable = "sys_user", dicText = "realname", dicCode = "username")
	@Dict(dictTable = "sys_user", dicText = "realname", dicCode = "username")
    @ApiModelProperty(value = "受理人")
    private String assignee;
	/**问题描述*/
	@Excel(name = "问题描述", width = 15)
    @ApiModelProperty(value = "问题描述")
    private String description;
	/**处理意见*/
	@Excel(name = "处理意见", width = 15)
    @ApiModelProperty(value = "处理意见")
    private String dealSuggestion;
	/**处理附件*/
	@Excel(name = "处理附件", width = 15)
    @ApiModelProperty(value = "处理附件")
    private String dealFile;
	/**处理完成时间*/
	@Excel(name = "处理完成时间", width = 15, format = "yyyy-MM-dd")
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @ApiModelProperty(value = "处理完成时间")
    private Date dealCompleteTime;
	/**附件*/
	@Excel(name = "附件", width = 15)
    @ApiModelProperty(value = "附件")
    private String file;
	/**附件模板(事项类别专用)*/
	@Excel(name = "附件模板(事项类别专用)", width = 15)
    @ApiModelProperty(value = "附件模板(事项类别专用)")
    private String modelFile;
	/**是否存案例库*/
	@Excel(name = "是否存案例库", width = 15)
    @ApiModelProperty(value = "是否存案例库")
    private String isknowledge;
	/**案例补充附件*/
	@Excel(name = "案例补充附件", width = 15)
    @ApiModelProperty(value = "案例补充附件")
    private String knowledgeFile;
	/**案例补充意见*/
	@Excel(name = "案例补充意见", width = 15)
    @ApiModelProperty(value = "案例补充意见")
    private String knowledgeSuggestion;
	/**事项类别ID*/
	@Excel(name = "事项类别ID", width = 15)
    @ApiModelProperty(value = "事项类别ID")
    private String ywItemType;
}
