<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="language" value="${pageContext.request.locale}"/>
<fmt:setLocale value="${language}"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <title>Perficient ITReturns</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
    <link rel="icon" type="image/png" href="/prftFinance/assets/img/favicon-32x32.png" sizes="32x32">
  <!-- Le styles -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="/prftFinance/assets/css/main.min.css" rel="stylesheet">

<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/pdfmake.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.36/vfs_fonts.js"></script>
<script src="https://cdn.datatables.net/buttons/1.5.2/js/buttons.html5.min.js"></script>
<link href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css" rel="stylesheet">

<style>
    div#submitted_wrapper, div#pending_wrapper {
        margin-top: 30px;
    }

    .dt-buttons {
        float: right;
    }

    div#submitted_filter, div#pending_filter  {
        margin-right: 10px;
    }
</style>

</head>

<body>
<header>
	<div class="header-top">
		<a href="#"><img
			src="/prftFinance/assets/img/logo2.png"></a>
	</div>
</header>

<div class="rightside">
		<div class="content-area">
			<section>
				<div class="row">
					<div class="col-md-12">
						<div class="tdc-panel profile-section calender-section">
							<h2> IT-Returns <img class="loadingImg"	id="fileUpload-loading" src="/prftFinance/assets/img/loading-circle.gif"></h2>
                            <div class="container">
                                <div class="col-md-6">
                                    <h3>Submitted</h3>
                                    <table id="submitted" class="display" style="width:100%">
                                    </table>
                                </div>
                                <div class="col-md-6">
                                    <h3>Pending</h3>
                                    <table id="pending" class="display" style="width:100%">
                                    </table>
                                </div>
                            </div>
                        <br/><br/>
						</div>
					</div>
			</section>
		</div>
	</div>

	<footer>
		<p> 2019 Perficient India. All right reserved.</p>
	</footer>
	<script>
	var dataSet_submitted = ${submitted}
    var dataSet_pending = ${pending}

    $(document).ready(function() {
        $('#submitted').DataTable( {
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'excelHtml5',
                    text: 'Export'
                }
            ],
            data: dataSet_submitted,
            columns: [
                { title: "ID" },
                { title: "Name" },
                { title: "Email" }
            ]
        } );

        $('#pending').DataTable( {
            dom: 'Bfrtip',
            buttons: [
                {
                    extend: 'excelHtml5',
                    text: 'Export'
                }
            ],
            data: dataSet_pending,
            columns: [
                { title: "ID" },
                { title: "Name" },
                { title: "Email" }
            ]
        } );
    } );
	</script>
</body>
</html>
