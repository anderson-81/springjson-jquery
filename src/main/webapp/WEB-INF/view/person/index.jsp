<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:_layout>
    <jsp:attribute name="body_area">
        <div class="container">
            <div class="row">
                <div class="col-md-offset-2 col-md-8 col-md-offset-2">
                    <div class="form-group">
                        <h1>List</h1>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-offset-2 col-md-8 col-md-offset-2">
                    <div class="form-group" id="div-table">
                        <table id="tbPerson" class="table" style="display:none;" data-page-length='5'>
                            <thead>
                                <tr>
                                    <th class="text-center">ID</th>
                                    <th>NAME</th>
                                    <th class="text-center">BIRTHDAY</th>
                                    <th class="text-center">SELECT</th>
                                </tr>
                            </thead>
                            <tbody>
                            </tbody>
                        </table>
                    </div>
                    <div class="form-group">
                        <a href="/person/new" class="btn btn-success pull-right col-md-2">Create</a>
                    </div>
                </div>
            </div>
        </div>
        <script src="../../../resources/scripts/index.js" type="text/javascript"></script>
        <link href="../../../resources/styles/index.css" rel="stylesheet" type="text/css"/>
        
        <script>
            
            
            
            
        </script>
        
        
        
    </jsp:attribute>
</t:_layout>