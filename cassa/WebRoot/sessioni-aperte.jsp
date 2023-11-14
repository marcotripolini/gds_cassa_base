<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<marquee scrollamount="7" style="margin-bottom:10px;">
	<ul style="padding-top:12px;padding-bottom:12px; ">
		<s:iterator value="ona" var="c">
				<s:if test="#c.tavolo == null">
					<li>
						<form action="login.action">
						  <input type="hidden" name="tessera" value="<s:property value='#c.tessera'/>">
						  <input type="hidden" name="order" value="<s:property value='#c.ordine'/>">
						  <input type="submit" style="font-size:1em !important" class="etichetta" value='<s:property value="#c.nome" /> <s:property value="#c.cognome" />-N. <s:property value="#c.ordine" /> - Tav: <s:property value="#c.tavolo" /> - Articoli: <s:property value="#c.quantiarticoli" />'>
						</form>
					</li>
				</s:if>
				<s:else>
					<li>
					  <button style="font-size:1em !important; background-color:green"
					  	class="etichetta" value=''>
					  	<s:property value="#c.nome" /> <s:property value="#c.cognome" />-N. <s:property value="#c.ordine" /> - Tav: <s:property value="#c.tavolo" /> - Articoli: <s:property value="#c.quantiarticoli" /></button>
					</li>
				</s:else>
		</s:iterator>
		<li>&nbsp;</li>
	</ul>
</marquee>
