
<div class="container-fluid">
	<div class="row">
		<div class="col-md-12">
			<div class="modal" id="loginmodal" tabindex="-1">
			
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Login</h4>
						</div>
						<div class="modal-body">
							<form method="POST" action='login'>
								<div class="form-group">
									<label for="inputusername">Username</label>
									<input class="form-control" placeholder="Login Username" type="text" id="inputusername" name="inputusername">
									
								</div>
								<div class="form-group">
									<label for="inputpassword">Password</label>
									<input class="form-control" placeholder="Password" type="password" id="inputpassword" name="inputpassword">
									
								</div>
								<div class="form-check">
  									<label class="form-check-label">
    								<input class="form-check-input" type="checkbox" name="rememberMe" id="rememberMe" value="yes">
    									Remember Me
  									</label>
								</div>
						</div>
						<div class="modal-footer">
							<div class="pull-left"><a href="views/register.jsp"><input type="button" value="Register" class="btn btn-primary" style="font-weight: bold"></a></div>
							<button class="btn btn-primary">Login</button>
							<button class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
							</form>
					</div>
				</div>
			</div>
		
		
		
		</div>
	</div>
</div>
<footer class="container-fluid text-center">
  <p>Online Store Copyright</p>  
  <form class="form-inline">Get deals:
    <input type="email" class="form-control" size="50" placeholder="Email Address">
    <button type="button" class="btn btn-danger">Sign Up</button>
  </form>
</footer>

</body>
</html>