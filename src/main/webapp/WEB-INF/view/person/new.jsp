<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:_layout>
    <jsp:attribute name="body_area">
        <div class="container">
            <div class="row">
                <form enctype="multipart/form-data">
                    <div class="col-md-offset-2 col-md-8 col-md-offset-2">
                        <div class="form-group">
                            <h1>Create</h1>
                        </div>
                        <div class="form-group">
                            <div id="errors" class="errors"><ul></ul></div> 
                        </div>
                        <div class="form-group">
                            <input type="hidden" id="token" name="token"><br>
                        </div>
                        <div class="form-group">
                            <label for="">Name</label>
                            <input type="text" class="form-control" id="name" name="name"><br>
                        </div>
                        <div class="form-group">
                            <label for="">E-mail</label>
                            <input type="text" id="email" name="email" class="form-control"><br>
                        </div>
                        <div class="form-group">
                            <label for="">Salary</label>
                            <input type="text" id="salary" name="salary" class="form-control"><br>
                        </div>
                        <div class="form-group">
                            <label for="">Birthday</label>
                            <input type="text" id="birthday" name="birthday" class="form-control" readonly="readonly"><br>
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
                            <input type="file" id="picture" name="picture" class="form-control">
                        </div>
                        <div class="form-group">
                            <input type="submit" value="Create" class="btn btn-success pull-right col-md-2"> 
                            <a href="/person/index" class="btn btn-primary pull-right col-md-2">Return</a> 
                            <input type="reset" value="Reset"  class="btn btn-secondary pull-right col-md-2">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <link href="../../../resources/styles/form.css" rel="stylesheet" type="text/css"/>
        <script src="../../../resources/scripts/components.js" type="text/javascript"></script>
        <script src="../../../resources/scripts/new.js" type="text/javascript"></script>
    </jsp:attribute>
</t:_layout>