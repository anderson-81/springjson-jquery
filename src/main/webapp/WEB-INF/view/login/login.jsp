<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_layout>
    <jsp:attribute name="body_area">
        <div class="container margin-10">
            <div class="row">
                <div class="col-md-offset-3 col-md-6 col-md-offset-3">
                    <div class="form-group">
                        <div id="errors" class="errors"><ul></ul></div>       
                    </div>
                </div>
            </div>
            <div class="row">
                <form>
                    <div class="col-md-offset-4 col-md-4 col-md-offset-4">
                        <div class="form-group">
                            <input type="hidden" id="token" name="token"><br>
                        </div>
                        <div class="form-group">
                            <label for="">Username</label>
                            <input type="text" id="username" name="username" class="form-control"><br>
                        </div>
                        <div class="form-group">
                            <label for="">Password</label>
                            <input type="password" id="password" name="password" class="form-control"><br>
                        </div>
                        <div class="form-group">
                            <input type="submit" class="btn btn-success col-md-3 pull-right" value="Login">
                            <a href="/index" style="color:black" class="btn btn-default col-md-3 pull-right">Cancel</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="../../../resources/scripts/login.js" type="text/javascript"></script>
        <link href="../../../resources/styles/form.css" rel="stylesheet" type="text/css"/>
    </jsp:attribute>
</t:_layout>