<%--
    Licensed to the Apache Software Foundation (ASF) under one or more
    contributor license agreements.  See the NOTICE file distributed with
    this work for additional information regarding copyright ownership.
    The ASF licenses this file to You under the Apache License, Version 2.0
    (the "License"); you may not use this file except in compliance with
    the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="language" value="${pageContext.request.locale}"/>
<fmt:setLocale value="${language}"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
  <meta charset="utf-8">
  <title>Moviefun</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">

  <!-- Le styles -->
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link href="../assets/css/main.min.css" rel="stylesheet">

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
							        <form class="" method="post" enctype="multipart/form-data" action="/itreturns/itrfileupload">
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
                                            <p>*</p>
                                        </div>
                                        <div class="form-group">
                                           <button type="submit" disabled class="btn btn-primary pull-right" id="btnuploadFile">Submit</button>
                                        </div>
                                      </form>
                                       <br/><br/>
							    </div>

                            </div>
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
    	    alert('Upload only .zip file.');
    	    $("#btnuploadFile").attr('disabled','disabled');
    	}else{
    	    $("#btnuploadFile").removeAttr('disabled');
    	}
    });
</script>
</body>
</html>
