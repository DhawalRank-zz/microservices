<jsp:include page="/views/header.jsp" />
<title>Login to UserUI</title>
</head>
<body>
<div class="jumbotron" align="center">
  <h1 class="display-3">Welcome to User UI</h1>
  <p class="lead">This is a simple login unit, a simple jumbotron-style component for calling extra attention to featured content or information.</p>
</div>
<div align="center" style="padding-top: 2.5em">
<% String errorMessage = (String) request.getAttribute("errorMessage"); 
	if(errorMessage != null) {
%>
<div class="alert alert-danger alert-dismissible fade show" role="alert" align="center" style="max-width: 25em">
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>  	
  <strong>Holy guacamole!!</strong> ${errorMessage}</div>
  <%} %>
	<div class="card bg-light mb-3" style="max-width: 20rem; height: 25em">
  <div class="card-header"><h4>Login to UserUI</h4></div>
  <div class="card-body">
	<form action="/login" method="POST">
	  <div class="form-group" style="padding-top: 2.5em">
	    <input type="text" class="form-control" name="username" id="username" placeholder="Enter username">
	  </div>
	  <div class="form-group" style="padding-top: 1.5em">
	    <input type="password" class="form-control" name="password" id="password" placeholder="Password">
	  </div>
	  <div style="padding-top: 1.5em">
	  <button type="submit" class="btn btn-primary btn-block">Submit</button>
	  </div>
	</form>    
  </div>
</div>
</div>

<jsp:include page="/views/footer.jsp" />
