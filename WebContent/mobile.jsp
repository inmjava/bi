<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd ">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page import="java.util.List" %>
<%@ page import="java.net.URLEncoder" %>

<%
	String device = (String) request.getAttribute("device");
	String decode = (String) request.getAttribute("decode");
	String protocolo = (String) request.getAttribute("protocolo");
	String urlBase = (String) request.getAttribute("urlBase");
	String urlFinal = (String) request.getAttribute("urlFinal");
	String servername = request.getServerName();
	List<String> listN = (List<String>) request.getAttribute("listN");
	List<String> listCid = (List<String>) request.getAttribute("listCid");
%>

<head>
	<meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
	<meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">  
	<title>Configurações Móveis <%= device %></title>
	<link rel="stylesheet" type="text/css" href="css/mstr.css">
</head>

<body class="mstrWeb Red">

	<table class="mstrToolbarWrapper" cellpadding="0" cellspacing="0" width="100%">
		<tbody>
			<tr>
				<td>
					<!-- barras do cabecalho -->
					<div class="mstrHeader" id="mstrWeb_shortcutsBar" name="mstrWeb_shortcutsBar" iframe="true">
						<span class="mstrShortcut"></span>				
						&nbsp;&nbsp;
						<span class="mstrShortcut"></span>
					</div>
					
					<!-- logo -->
					<div class="mstrPath" id="mstrWeb_path" name="mstrWeb_path" iframe="true">
						
          <div class="mstrPathContainer"> <a id="mstrStarburst" title="Projetos" class="mstrLink"> 
            <div id="mstrLogo" class="mstrLogo path" title="Projetos"> </div>
            <div id="mstrLogoSmall" class="mstrLogoSmall path" title="Projetos"> 
            </div>
            </a> 
            <div class="mstrPathText"> <span class="mstrPathLast">Configurações
              Móveis <%= /*device*/"" %></span> </div>
							<div class="mstrSpacer"></div>
						</div>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
	
			<div style="max-width:900px; margin:0 auto; text-align:left; padding:14px 0 0 14px ">
			
			<%
				for (int i = 0; i < listN.size(); i++) {

					//String url = "http://" + servername + ":80/MicroStrategyMobile/servlet/taskProc?taskId=getMobileConfiguration&taskEnv=xml&taskContentType=xmlanf&configurationID=" + listCid.get(i);
					//url = (decode == null ? protocolo + "://?url=" + URLEncoder.encode(url) + "&authMode=1" : url);
					String url = String.format(urlBase, servername, listCid.get(i));
					url = (decode == null ? "mstr://?url=" + URLEncoder.encode(url) + urlFinal : url);
			%>
					
					<a class="mstrContent" title="<%= listN.get(i) %>" href="<%= url %>">
					<table class="mstrLargeIconView" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr>
								<td colspan="1" rowspan="1">
									<table class="mstrLargeIconViewItem" cellspacing="0">
										<tbody>	
											<tr>
												<td colspan="1" rowspan="1">
													<a class="mstrLink">
														<span id="mstrIconProject" name="mstrIconProject" class="mstrIcon"></span>
													</a>
												</td>
												<td colspan="1" class="mstrLargeIconViewItemText" rowspan="1">
													<div class="mstrLargeIconViewItemName">
														<a class="mstrLink"><%= listN.get(i).split(" - ")[0] %></a>
													</div>
													<div class="mstrProjectDescription"><%= listN.get(i).split(" - ")[1] %></div>														
												</td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
						</tbody>
					</table>
				</a>
					
			<%
				}
			%>
				
				<br clear="all"/>
				
			</div>		
		
	</body>
</html>