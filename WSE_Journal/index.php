<html>
<head lang="en">
    <meta http-equiv="Content-Type" content="text/html; charset=Unicode" />
    <link href="statics/css/ninja-slider.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="statics/css/amazeui.min.css">
    <link rel="stylesheet" href="statics/css/my.css">
<!--    <link rel="icon" type="image/png" href="statics/i/favicon.png">-->
<!--    <link rel="icon" sizes="192x192" href="statics/i/app-icon72x72@2x.png">-->
    <link rel="stylesheet" href="statics/css/app.css">
    <script src="statics/js/ninja-slider.js" type="text/javascript"></script>
</head>

  <body>
  <?php $query = $_POST['query'];
  if (empty($query)){
  echo '
    <table width="500" height="0%" border="0" align="center"  cellpadding="0" cellspacing="0">
    <form name="form1" action="index.php" method="POST" >
      <tr><td height="25%"></td></tr>
      <tr><td height="10%"><h2>JournalSearch</h2></td></tr>
      <tr>
        <td height="30" align="center"><div class="am-input-group">
        <input type="text" name="query" class="am-form-field" size="37" value="';echo $query;echo '">
        <span class="am-input-group-btn">
          <button class="am-btn am-btn-default am-btn-danger" type="submit" data-am-popover="{content: \'~ fuzzy search<br>* wildcard search\', trigger: \'hover focus\'}"><span class="am-icon-search"></span> 
            
          </button>
        </span>
      </div></td>
      </tr>
      <tr><td height="2%"></td></tr>
      <tr></tr>
      <tr></tr>

    <tr><td height="25" align="center"><p>
      Last Updated:'; $a=filemtime("cacheFile");
      if( ! ini_get('date.timezone') )
      {
          date_default_timezone_set('GMT');
      };
            echo date("Y-m-d H:i:s",$a); echo '
        </p>
    </td></tr>
    <tr><td height="25" align="center"><p>
    <small>&#169; 2016 HANSI MOU ALL RIGHTS RESERVED</small></p>
    </td></tr>
    </table>
    </form>
  ';
  }
  else {
      echo '
<header class="am-topbar am-topbar-fixed-top">
<div class="am-container">
  <h1 class="am-topbar-brand">
    <a href="" font-family="Optima">JournalSearch</a>
  </h1>
  
  <form action="index.php" method="POST" class="am-topbar-form am-topbar-left am-form-inline" role="search">
    <div class="am-form-group">
      <div class="am-input-group">
        <input type="text" name="query" class="am-form-field" size="37" value="';echo $query;echo '">
        <span class="am-input-group-btn">
          <button class="am-btn am-btn-default am-btn-danger" type="submit"><span class="am-icon-search"></span> </button>
        </span>
      </div>
    </div>
  </form>
  
</div>
</header>';
echo '



<div class="am-g am-g-fixed blog-g-fixed get">
<div class="am-u-md-10">';

$w = system("cd /var/www/html/search; /usr/bin/java -jar target/search-0.0.1-SNAPSHOT.jar \"".$_POST['query'] . "\"",$res);



echo '</div>

</div>
<footer class="blog-footer">
<p>Last Updated: ';$a=filemtime("cacheFile");
      echo date("Y-m-d H:i:s",$a); echo '<br/>
  <small>&#169; Web Search Engine: Journal Search</small>
</p>
</footer>
';

  }
  ?>
      
    <!--[if (gte IE 9)|!(IE)]><!-->
    <script src="statics/js/jquery.min.js"></script>
    <!--<![endif]-->
    <!--[if lte IE 8 ]>
  <script src="http://libs.baidu.com/jquery/1.11.3/jquery.min.js"></script>

  <script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>

  <script src="statics/js/amazeui.ie8polyfill.min.js"></script>

  <![endif]-->
    <script src="statics/js/amazeui.min.js"></script>

  </body>

</html>