<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="mvcproject.java11.crm.urls.UrlsController"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<div class="container-fluid">
	<div class="row bg-title">
		<div class="col-lg-3 col-md-4 col-sm-4 col-xs-12">
			<h4 class="page-title">Danh sách thành viên</h4>
		</div>
		<div class="col-lg-9 col-sm-8 col-md-8 col-xs-12 text-right">
			<a
				href="<%=request.getContextPath() + UrlsController.URL_ACCOUNT_ADD %>"
				class="btn btn-sm btn-success">Thêm mới</a>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /row -->
	<div class="row">
		<div class="col-sm-12">
			<div class="white-box">
				<div class="table-responsive">
					<div class="col-md-6">
					
						<form method="get"
							action="<%=request.getContextPath() + UrlsController.URL_ACCOUNT_VIEW %>">
							<input type="hidden" name="current_page" id="current_page"
								value="1"> <input type="submit"
								value="hien thi"> <select name="record_on_page"
								id="record_on_page" aria-controls="example" class="">
								<option value="10">10</option>
								<option value="25">25</option>
								<option value="50">50</option>
								<option value="100">100</option>
							</select> <input type="hidden" name="keyword_search" id="keyword_search"
								value="${keyword_search}">
						</form>
						
					</div>
					<div class="col-md-6 text-right">
					
						<form class="form-inline" method="get">
							<input type="hidden" name="current_page" id="current_page"
								value='${current_page}'> <input type="hidden"
								name="record_on_page" id="record_on_page"
								value='${ record_on_page}'> 
								<input type="text"
								placeholder="search"
								name="keyword_search" aria-controls="editable-sample" > 
						</form>
						
					</div>

					<table class="table" id="example">
						<thead>
							<tr>
								<th>STT</th>
								<th>FullName</th>
								<th>Email</th>
								<th>Password</th>
								<th>Phone</th>
								<th>Address</th>
								<th>Role</th>
								<th>#Action</th>
							</tr>
						</thead>
						<tbody>
							<tr class="">
								<c:forEach var="account" items="${viewsAccount}"
									varStatus="loop">
									<tr>
										<th scope="row">${loop.index + 1}</th>
										<td>${account.fullname}</td>
										<td>${account.email}</td>
										<td>${account.password}</td>
										<td>${account.phone}</td>
										<td>${account.address}</td>
										<td>${account.role_name}</td>
										<td><a
											href="<%= request.getContextPath() + UrlsController.URL_ACCOUNT_EDIT %>?id=${account.id}"
											class="btn btn-sm btn-primary ">Sửa</a> <a
											href="<%= request.getContextPath() + UrlsController.URL_ACCOUNT_DELETE %>?id=${account.id}"
											class="btn btn-sm btn-danger">Xóa</a> <a
											href="<%= request.getContextPath() + UrlsController.URL_ACCOUNT_DETAIL %>?id=${account.id}"
											class="btn btn-sm btn-info">Xem</a></td>
									</tr>
								</c:forEach>
							</tr>
						</tbody>
					</table>
					
					<nav aria-label="Page navigation example" align="right">
						<ul class="pagination">
							<c:if test="${current_page != 1 }">
								<li class="next active"><a
									href="<%=request.getContextPath() +  UrlsController.URL_ACCOUNT_VIEW%>?current_page=${current_page - 1 }&record_on_page=${record_on_page}&keyword_search=${keyword_search}">←
												Prev</a></li>
							</c:if>
							<c:forEach var="i" begin="${current_page}" end="${totalPage < 3 ? totalPage: current_page + 2 }">
								<c:choose>
									<c:when test="${current_page == i}">
										<li class="page-item active"><a
											href="<%=request.getContextPath() +  UrlsController.URL_ACCOUNT_VIEW%>?current_page=${i}&record_on_page=${record_on_page}&keyword_search=${keyword_search}">${i}</a></li>
									</c:when>
									
									<c:otherwise>
										<li class="page-item"><a
											href="<%=request.getContextPath() +  UrlsController.URL_ACCOUNT_VIEW%>?current_page=${i}&record_on_page=${record_on_page}&keyword_search=${keyword_search}">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:if test="${current_page + 2 != totalPage}">
								<li class="next active"><a
									href="<%=request.getContextPath() +  UrlsController.URL_ACCOUNT_VIEW%>?current_page=${current_page + 1 }&record_on_page=${record_on_page}&keyword_search=${keyword_search}">Next
										→ </a></li>
							</c:if>
							<c:if test="${totalPage == 0}">
								<li class=""><a
									href="<%=request.getContextPath() +  UrlsController.URL_ACCOUNT_VIEW%>">Back ♣</a></li>
							</c:if>
						</ul>
					</nav>
					
				</div>
			</div>
		</div>
	</div>
	<!-- /.row -->
</div>

</html>