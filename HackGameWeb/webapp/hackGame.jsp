<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8" />
  <title>OathPathFinders</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <meta name="description" content="Oath Inc. Path Finders"/>
  <link rel="stylesheet" type="text/css" href="css/hack_game.css">
</head>
<body>
<h1>Oath Path Finders</h1>
<svg width="1200" height="1200">
  <rect id="rect00" x="50" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect01" x="110" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect02" x="170" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect03" x="230" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect04" x="290" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect05" x="350" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect06" x="410" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect07" x="470" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect08" x="530" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect09" x="590" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect010" x="650" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect011" x="710" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect012" x="770" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect013" x="830" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect014" x="890" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect015" x="950" y="50" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect10" x="50" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect11" x="110" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect12" x="170" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect13" x="230" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect14" x="290" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect15" x="350" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect16" x="410" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect17" x="470" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect18" x="530" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect19" x="590" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect110" x="650" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect111" x="710" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect112" x="770" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect113" x="830" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect114" x="890" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect115" x="950" y="110" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect20" x="50" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect21" x="110" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect22" x="170" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect23" x="230" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect24" x="290" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect25" x="350" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect26" x="410" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect27" x="470" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect28" x="530" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect29" x="590" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect210" x="650" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect211" x="710" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect212" x="770" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect213" x="830" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect214" x="890" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect215" x="950" y="170" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect30" x="50" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect31" x="110" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect32" x="170" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect33" x="230" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect34" x="290" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect35" x="350" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect36" x="410" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect37" x="470" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect38" x="530" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect39" x="590" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect310" x="650" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect311" x="710" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect312" x="770" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect313" x="830" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect314" x="890" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect315" x="950" y="230" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect40" x="50" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect41" x="110" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect42" x="170" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect43" x="230" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect44" x="290" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect45" x="350" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect46" x="410" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect47" x="470" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect48" x="530" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect49" x="590" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect410" x="650" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect411" x="710" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect412" x="770" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect413" x="830" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect414" x="890" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect415" x="950" y="290" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect50" x="50" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect51" x="110" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect52" x="170" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect53" x="230" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect54" x="290" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect55" x="350" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect56" x="410" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect57" x="470" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect58" x="530" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect59" x="590" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect510" x="650" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect511" x="710" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect512" x="770" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect513" x="830" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect514" x="890" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect515" x="950" y="350" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect60" x="50" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect61" x="110" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect62" x="170" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect63" x="230" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect64" x="290" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect65" x="350" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect66" x="410" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect67" x="470" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect68" x="530" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect69" x="590" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect610" x="650" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect611" x="710" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect612" x="770" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect613" x="830" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect614" x="890" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect615" x="950" y="410" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect70" x="50" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect71" x="110" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect72" x="170" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect73" x="230" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect74" x="290" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect75" x="350" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect76" x="410" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect77" x="470" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect78" x="530" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect79" x="590" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect710" x="650" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect711" x="710" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect712" x="770" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect713" x="830" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect714" x="890" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect715" x="950" y="470" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect80" x="50" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect81" x="110" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect82" x="170" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect83" x="230" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect84" x="290" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect85" x="350" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect86" x="410" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect87" x="470" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect88" x="530" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect89" x="590" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect810" x="650" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect811" x="710" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect812" x="770" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect813" x="830" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect814" x="890" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect815" x="950" y="530" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect90" x="50" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect91" x="110" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect92" x="170" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect93" x="230" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect94" x="290" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect95" x="350" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect96" x="410" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect97" x="470" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect98" x="530" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect99" x="590" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect910" x="650" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect911" x="710" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect912" x="770" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect913" x="830" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect914" x="890" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect915" x="950" y="590" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect100" x="50" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect101" x="110" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect102" x="170" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect103" x="230" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect104" x="290" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect105" x="350" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect106" x="410" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect107" x="470" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect108" x="530" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect109" x="590" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1010" x="650" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1011" x="710" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1012" x="770" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1013" x="830" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1014" x="890" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1015" x="950" y="650" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect110" x="50" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect111" x="110" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect112" x="170" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect113" x="230" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect114" x="290" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect115" x="350" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect116" x="410" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect117" x="470" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect118" x="530" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect119" x="590" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1110" x="650" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1111" x="710" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1112" x="770" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1113" x="830" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1114" x="890" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1115" x="950" y="710" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect120" x="50" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect121" x="110" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect122" x="170" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect123" x="230" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect124" x="290" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect125" x="350" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect126" x="410" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect127" x="470" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect128" x="530" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect129" x="590" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1210" x="650" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1211" x="710" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1212" x="770" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1213" x="830" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1214" x="890" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1215" x="950" y="770" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect130" x="50" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect131" x="110" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect132" x="170" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect133" x="230" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect134" x="290" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect135" x="350" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect136" x="410" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect137" x="470" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect138" x="530" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect139" x="590" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1310" x="650" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1311" x="710" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1312" x="770" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1313" x="830" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1314" x="890" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1315" x="950" y="830" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect140" x="50" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect141" x="110" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect142" x="170" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect143" x="230" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect144" x="290" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect145" x="350" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect146" x="410" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect147" x="470" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect148" x="530" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect149" x="590" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1410" x="650" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1411" x="710" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1412" x="770" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1413" x="830" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1414" x="890" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1415" x="950" y="890" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />  
  
  <rect id="rect150" x="50" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect151" x="110" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect152" x="170" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect153" x="230" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect154" x="290" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect155" x="350" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect156" x="410" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect157" x="470" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect158" x="530" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect159" x="590" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1510" x="650" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1511" x="710" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1512" x="770" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1513" x="830" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1514" x="890" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" />
  <rect id="rect1515" x="950" y="950" width="60" height="60" style="fill:rgb(255,255,255);stroke-width:1;stroke:rgb(0,0,0)" /> 
  
  <line id="player1line" x1="70" y1="70" x2="71" y2="70" stroke-width="5" stroke="red"/>
  <line id="player2line" x1="70" y1="90" x2="71" y2="90" stroke-width="5" stroke="blue"/> 
</svg>





 <br>
 <button id="initbtn" class="initbtn" type="button" onclick="initGame()">Init</button>
  <br>
  <br>
 <button id="startbtn" class="startbtn" type="button" onclick="startGame()">Start</button>
  <br>
  <img src="images/oath_logo.jpg" class="oath_image">
<script src="js/hack_game.js"></script>
</body>
</html>