<#import "/backend/base.ftl" as base>

<#assign xcss>
<style>
    body{
        background: url('/assets/images/new_year_background.png') repeat;
    }
    .btn-lg, .panel, .form-control {
        border-radius: 2px;
    }
</style>
</#assign>

<@base.html title="登录" css=xcss>
<div class="container">
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="login-panel panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">请登录</h3>
                </div>
                <div class="panel-body">
                    <form role="form">
                        <fieldset>
                            <div class="form-group">
                                <input class="form-control" placeholder="用户名(JIRA)" name="username" type="text" autofocus="true">
                            </div>
                            <!-- Change this to a button or input when using this as a form -->
                            <button id="login" href="index" class="btn btn-lg btn-success btn-block">Login</button>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById('login').addEventListener('click', function() {
        alert('haha')
		return false
    });
</script>
</@base.html>