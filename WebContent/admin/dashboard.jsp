<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<div class="container-fluid">
	<div class="row">
		<nav id="sidebarmenu"
			class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
			<div class="sidebar-sticky pt-3">
				<ul class="nav flex-column">
					<li class="nav-item"><a class="nav-link active"
						href="/dog/admin/memberlist">"회원관리"</a></li>
					<li class="nav-item"><a class="nav-link active" href="#">"Dashboard2"</a>
					</li>
					<li class="nav-item"><a class="nav-link active" href="#">"Dashboard3"</a>
					</li>
					<li class="nav-item"><a class="nav-link active" href="#">"Dashboard4"</a>
					</li>
					<li class="nav-item"><a class="nav-link active" href="#">"Dashboard5"</a>
					</li>
				</ul>
			</div>
		</nav>
		<div style="width: 70%;">
			<canvas id="productChart"></canvas>
			${cdates}
		</div>
	</div>
</div>

<script>
var ctx = document.getElementById('productChart');

var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ${cdates}, 
        datasets: [{
            label: '등록글 개수',
            data: ${counts},
            backgroundColor: [
                'rgba(54, 162, 235, 0.2)'
            ],
            borderColor: [
                'rgba(54, 162, 235, 1)'
            ],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
</script>

<%@ include file="../include/footer.jsp"%>