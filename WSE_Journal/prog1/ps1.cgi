#!/bin/bash
echo "Content-type: text/html"
echo ""

echo '<!DOCTYPE html>'

echo '<html>'

echo '<head>'

echo '<title>My mini search engine</title>'

echo '<link rel="icon" href="http://codeforces.com/userphoto/title/WJMZBMR/photo.jpg" type="image/x-icon">'

echo '<meta name="ROBOTS" content="NOINDEX, NOFOLLOW" />'

echo '<!-- CSS styles for standard search box -->'

echo '<style type="text/css">'

echo '	#tfheader{'

echo '		background-color:#c3dfef;'

echo '	}'

echo '	#tfnewsearch{'

echo '		float:left;'

echo '		padding:20px;'

echo '	}'

echo '	.tftextinput{'

echo '		margin: 0;'

echo '		padding: 5px 15px;'

echo '		font-family: Arial, Helvetica, sans-serif;'

echo '		font-size:14px;'

echo '		border:1px solid #0076a3; border-right:0px;'

echo '		border-top-left-radius: 5px 5px;'

echo '		border-bottom-left-radius: 5px 5px;'

echo '	}'

echo '	.tfbutton {'

echo '		margin: 0;'

echo '		padding: 5px 15px;'

echo '		font-family: Arial, Helvetica, sans-serif;'

echo '		font-size:14px;'

echo '		outline: none;'

echo '		cursor: pointer;'

echo '		text-align: center;'

echo '		text-decoration: none;'

echo '		color: #ffffff;'

echo '		border: solid 1px #0076a3; border-right:0px;'

echo '		background: #0095cd;'

echo '		background: -webkit-gradient(linear, left top, left bottom, from(#00adee), to(#0078a5));'

echo '		background: -moz-linear-gradient(top,  #00adee,  #0078a5);'

echo '		border-top-right-radius: 5px 5px;'

echo '		border-bottom-right-radius: 5px 5px;'

echo '	}'

echo '	.tfbutton:hover {'

echo '		text-decoration: none;'

echo '		background: #007ead;'

echo '		background: -webkit-gradient(linear, left top, left bottom, from(#0095cc), to(#00678e));'

echo '		background: -moz-linear-gradient(top,  #0095cc,  #00678e);'

echo '	}'

echo '	/* Fixes submit button height problem in Firefox */'

echo '	.tfbutton::-moz-focus-inner {'

echo '	  border: 0;'

echo '	}'

echo '	.tfclear{'

echo '		clear:both;'

echo '	}'

echo '</style>'

echo '</head>'

echo '<body>'

echo '	<!-- HTML for SEARCH BAR -->'

echo '	<div id="tfheader">'

echo '		<form id="tfnewsearch" method="get" action='${SCRIPT}'>'

echo '		        <input type="text" class="tftextinput" name="val_x" size="21" maxlength="120"><input type="submit" value="search" class="tfbutton">'

echo '		</form>'

echo '	<div class="tfclear"></div>'

echo '	</div>'



    if [ -z "$QUERY_STRING" ]; then
    exit 0
else
    # java test ${QUERY_STRING:6:100}
    # java -cp "./Lucene/lucene-analyzers-common-5.4.1.jar:./Lucene/jtidy-r938.jar:./Lucene/lucene-core-5.4.1.jar:./Lucene/lucene-demo-5.4.1.jar:./Lucene/:./Lucene/lucene-queryparser-5.4.1.jar:./Lucene/HTMLParser.jar" mySearchIndex -index index -query ${QUERY_STRING:6:100}
     bash scripts/search.sh ${QUERY_STRING:6:100}
#    cd out
#    java -cp ../lib/*:../out/* mySearchIndex -index index -query ${QUERY_STRING:6:100}
fi

    echo '</body>'

echo '</html>'

