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
    <link rel="icon" type="image/png" href="../assets/img/favicon-32x32.png" sizes="32x32">
  <!-- Le styles -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="../assets/css/main.min.css" rel="stylesheet">
    <style>
        .instructions .list-group-item {
            background-color: whitesmoke !important;
            border: none;
        }
    </style>
</head>

<body>
<header>
	<div class="header-top">
		<a href="#"><img
			src="../assets/img/logo2.png"></a>
	</div>
</header>

<div class="rightside">
		<div class="content-area">
			<section>
				<div class="row">
					<div class="col-md-12">
						<div class="tdc-panel profile-section calender-section">
							<h2> IT-Returns <img class="loadingImg"	id="fileUpload-loading" src="../assets/img/loading-circle.gif"></h2>
							<div class="container">
							    <div class="col-md-6">
							        <form class="" method="post" enctype="multipart/form-data" action="/prftFinance/itrfileupload">
                                        <div class="form-group">
                                          <label for="fname"><strong>Firstname</strong></label>
                                          <input type="text" class="form-control" id="fname" placeholder="Enter Firstname" name="fname">
                                        </div>
                                        <div class="form-group">
                                          <label for="lname"><strong>Lastname</strong></label>
                                          <input type="text" class="form-control" id="lname" placeholder="Enter Lastname" name="lname">
                                        </div>
                                        <div class="form-group" id="fileUploadPanel">
                                            <div class="userprof-info">
                                                <span class="btn btn-primary btn-file btn-file-upload">Browse
                                                 <input type="file" name="myfile" class="browseFile">
                                                </span> <label class="fileName"></label>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <c:if test="${not empty message}">
                                                <div class="alert alert-info">
                                                    <c:out value="${message}"/>
                                                </div>
                                            </c:if>
                                            <c:if test="${not empty errorMsg}">
                                                <div class="alert alert-danger">
                                                    <c:out value="${errorMsg}"/>
                                                </div>
                                            </c:if>
                                            <div class="alert alert-danger fileFormatErr" style="display:none">
                                                Alert!!! Upload only .zip file.
                                            </div>
                                        </div>
                                        <div class="form-group">
                                           <button type="submit" disabled class="btn btn-primary pull-right" id="btnuploadFile">Submit</button>
                                        </div>
                                      </form>
							    </div>
							    <div class="col-md-6">
                                    <div class="instructions well well-lg">
                                        <h3>Instructions to Upload Proof</h3>
                                        <ul class="list-group">
                                            <li class="list-group-item">1. Update the required details in excel sheets</li>
                                            <li class="list-group-item">2. Scan all neccessary proof as pdf</li>
                                            <li class="list-group-item">3. Archive all documents into a ZIP file</li>
                                            <li class="list-group-item">4. File name should be Firstname Lastname.zip</li>
                                        </ul>
                                    </div>
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
	$(document).on('change', '.browseFile', function() {
	    var filename = $(this).val();
	    $(".fileName").text($(this).val().replace(/C:\\fakepath\\/i, ''));
    	if(filename.split(".").pop() != "zip"){
    	    $(".fileFormatErr").show();
    	    $("#btnuploadFile").attr('disabled','disabled');
    	}else{
    	    $(".fileFormatErr").hide();
    	    $("#btnuploadFile").removeAttr('disabled');
    	}
    });
</script>
</body>
</html>
