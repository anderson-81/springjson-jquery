<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:_layout>
    <jsp:attribute name="body_area">
        <div class="container">
            <div class="row">
                <form enctype="multipart/form-data" style="display:none;">
                    <div class="col-md-offset-2 col-md-8 col-md-offset-2">
                        <div class="form-group">
                            <h1>Edit</h1>
                        </div>
                        <div class="form-group" style="display:none;">
                            <div id="errors" class="errors"><ul></ul></div> 
                        </div>
                        <div class="form-group">
                            <input type="hidden" id="token" name="token">
                        </div>
                        <div class="form-group">
                            <input type="hidden" id="id" name="id">
                        </div>
                        <div class="form-group">
                            <label for="">Name</label>
                            <input type="text" class="form-control" id="name" name="name">
                        </div>
                        <div class="form-group">
                            <label for="">E-mail</label>
                            <input type="text" id="email" name="email" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="">Salary</label>
                            <input type="text" id="salary" name="salary" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="">Birthday</label>
                            <input type="text" id="birthday" name="birthday" class="form-control">
                        </div>
                        <div class="form-group">
                            <label for="">Gender</label>
                            <select name="gender" id="gender" class="form-control">
                                <option value="M">MALE</option>
                                <option value="F">FEMALE</option>
                            </select><br>
                        </div>
                        <div class="form-group">
                            <label for="">Picture</label>
                            <img id="avatar" class="img-edit pull-right">
                            <input type="file" id="picture" name="picture" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Edit" class="btn btn-warning pull-right col-md-2"> 
                            <a href="/person/index" class="btn btn-primary pull-right col-md-2">Return</a> 
                            <a id="lnkDelete" href="#" class="btn btn-danger pull-right col-md-2">DELETE</a>
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <link href="../../../resources/styles/form.css" rel="stylesheet" type="text/css"/>
        <script src="../../../resources/scripts/components.js" type="text/javascript"></script>
        <script src="../../../resources/scripts/edit.js" type="text/javascript"></script>
    </jsp:attribute>
</t:_layout>