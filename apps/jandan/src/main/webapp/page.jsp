<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ page import="xyz.zerxoi.service.BaseService, xyz.zerxoi.pojo.*, java.util.*, org.ocpsoft.prettytime.PrettyTime" %>
<%
    PrettyTime p = new PrettyTime().setLocale(Locale.SIMPLIFIED_CHINESE);
    Comment comment = BaseService.getComment();
    List<Pic> pics = BaseService.getCommentPics();
    List<Tucao> tucaos = BaseService.getCommentTucaos();
    List<Tucao> hotTucaos = BaseService.getCommentHotTucaos();
    response.setHeader("Access-Control-Allow-Origin", "*");
%>
<div class="page">
    <div class="comment-scroll rtl">
        <div class="comment">
            <div class="header">
                <div>
                    <div class="author"><%= comment.getAuthor() %></div>
                    <div class="date"><%= p.format(new Date(comment.getDate())) %></div>
                </div>
                <div>
                    <div class="id"><%= comment.getId() %></div>
                </div>
            </div>
            <div class="content">
                <%= comment.getContent() == null ? "" : comment.getContent() %>
                <%
                    for (Pic pic : pics) {
                %>
                <div>
                    <img src="<%= pic.getUrl() %>">
                </div>
                <%
                    }
                %>
            </div>
            <div class="footer">
                <div class="stat">
                    <span><span class="read">浏览</span>[<%= comment.getRead() %>]</span>
                    <span><span class="score">分数</span>[<%= String.format("%.2f", comment.getScore()) %>]</span>
                </div>
                
                <div class="vote">
                    <span><span class="oo">OO</span>[<%= comment.getOo() %>]</span>
                    <span><span class="xx">XX</span>[<%= comment.getXx() %>]</span>
                    <span><span class="tucao">吐槽</span>[<%= comment.getTucao() %>]</span>
                </div>
            </div>
        </div>
    </div>
    <div class="tucao-scroll">
        <div class="tucao">
            <% if (hotTucaos.size() > 0) { %>
                <div class="hot-list">
                    <div class="hot-title">热评</div>
                    <%
                        for (Tucao tucao : hotTucaos) {
                    %>
                    <div class="hot-row">
                        <div class="header">
                            <div class="author"><%= tucao.getAuthor() %></div>
                        </div>
                        
                        <div class="content"><%= tucao.getContent() %></div>
                        <div class="footer">
                            <span><%= p.format(new Date(tucao.getDate()*1000)) %></span>
                            <span><span class="oo">OO</span>[<%= tucao.getOo() %>]</span>
                            <span><span class="xx">XX</span>[<%= tucao.getXx() %>]</span>
                        </div>
                    </div>
                        
                    <%
                        }
                    %>
                </div>
            <% } %>
            <div class="tucao-list">
                <%
                    for (int i = 0; i < tucaos.size(); i++) {
                        Tucao tucao = tucaos.get(i);
                %>
                <div class="tucao-row" id="tucao-<%= tucao.getId() %>">
                    <div class="header">
                        <div class="author"><%= tucao.getAuthor() %></div>
                        <div class="floor">#<%= i + 1 %>楼</div>
                    </div>
                    
                    <div class="content"><%= tucao.getContent() %></div>
                    <div class="footer">
                        <span><%= p.format(new Date(tucao.getDate()*1000)) %></span>
                        <span><span class="oo">OO</span>[<%= tucao.getOo() %>]</span>
                        <span><span class="xx">XX</span>[<%= tucao.getXx() %>]</span>
                    </div>
                </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>
</div>