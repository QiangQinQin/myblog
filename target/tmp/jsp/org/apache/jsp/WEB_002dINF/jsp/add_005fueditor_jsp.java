package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class add_005fueditor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=GB18030\">\r\n");
      out.write("    <link href=\"");
      out.print(basePath);
      out.write("css/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href=\"");
      out.print(basePath);
      out.write("css/style.css\" rel=\"stylesheet\">\r\n");
      out.write("    <title>新增博客</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<div class=\"ibox-content\">\r\n");
      out.write("    <form method=\"get\" class=\"form-horizontal\">\r\n");
      out.write("        <div class=\"hr-line-dashed\"></div>\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <label class=\"col-sm-2 control-label\">内容:</label>\r\n");
      out.write("            <div class=\"col-sm-10\">\r\n");
      out.write("                ");
      out.write("\r\n");
      out.write("                <script id=\"editor\" type=\"text/plain\"></script>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("\r\n");
      out.write("        <div class=\"form-group\">\r\n");
      out.write("            <div class=\"col-sm-4 col-sm-offset-2\">\r\n");
      out.write("                <button class=\"btn btn-primary\" type=\"button\" onclick=\"saveArticle()\">保存内容</button>\r\n");
      out.write("                <button class=\"btn btn-white\" type=\"button\" onclick=\"cancelSaveArticle()\">取消</button>\r\n");
      out.write("            </div>\r\n");
      out.write("        </div>\r\n");
      out.write("    </form>\r\n");
      out.write("</div>\r\n");
      out.write("\r\n");
      out.write("<!-- Mainly scripts -->\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("js/jquery-2.1.1.min.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("js/bootstrap.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("js/article/add_article_test.js\"></script>\r\n");
      out.write("<!-- 配置文件 -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("js/ueditor1_4_3/ueditor.config.js\"></script>\r\n");
      out.write("<!-- 编辑器源码文件 -->\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.print(basePath);
      out.write("js/ueditor1_4_3/ueditor.all.js\"></script>\r\n");
      out.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\"");
      out.print(basePath);
      out.write("js/ueditor1_4_3/lang/zh-cn/zh-cn.js\"></script>\r\n");
      out.write("<script src=\"");
      out.print(basePath);
      out.write("js/plugins/chosen/chosen.jquery.js\"></script>\r\n");
      out.write("\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("    $(document).ready(function () {\r\n");
      out.write("        var ue = UE.getEditor('editor');\r\n");
      out.write("    })\r\n");
      out.write("\r\n");
      out.write("    // 初始化多选框\r\n");
      out.write("    $(\".chosen-select\").chosen({\r\n");
      out.write("        max_selected_options: 5,\r\n");
      out.write("        no_results_text: \"没有找到\",\r\n");
      out.write("        allow_single_deselect: true\r\n");
      out.write("    });\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
