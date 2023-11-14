<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
	<s:iterator value="ttt">
		<s:if test="%{occupato==1}">
			<div class="col-md-2 col-sm-2" style="padding-right:0px; padding-left:0px" >
				<form action="login.action">
				  <input type="hidden" name="table" value="<s:property value='id'/>">
				  <input type="hidden" name="tessera" value="<s:property value='tessera'/>">
					<button class="btn btn-sm btn-warning btn-custom btn-full tav">
						<s:property value="%{nome_tavolo}" />
					</button>
				</form>
			</div>
		</s:if>
		<s:else>
			<div class="col-md-2 col-sm-2"
				style="padding-right:0px; padding-left:0px; font-size: 10pt">
				<button class="btn btn-sm btn-success btn-custom btn-full tav">
					<s:property value="%{nome_tavolo}" />
				</button>
			</div>
		</s:else>
	</s:iterator>
