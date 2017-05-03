// Compiled by ClojureScript 1.9.229 {:static-fns true, :optimize-constants true}
goog.provide('thirdpartyjs.core');
goog.require('cljs.core');
cljs.core.enable_console_print_BANG_();
document.getElementById("app").innerHTML = "<h1>Let's do this</h1>";
thirdpartyjs.core.values = [(0),(3),(2),(5),(4),(7),(8),(6),(2),(6),(5),(1),(0)];
thirdpartyjs.core.width = (960);
thirdpartyjs.core.height = (500);
var svg_12291 = d3.select("#app").append("svg").attr("width",thirdpartyjs.core.width).attr("height",thirdpartyjs.core.height);
var x_scale_12292 = d3.scaleLinear().range([(0),thirdpartyjs.core.width]).domain([(0),(12)]);
var y_scale_12293 = d3.scaleLinear().range([thirdpartyjs.core.height,(0)]).domain([(0),(10)]);
var line_12294 = d3.line().x(((function (svg_12291,x_scale_12292,y_scale_12293){
return (function (p1__12290_SHARP_,p2__12289_SHARP_){
return (x_scale_12292.cljs$core$IFn$_invoke$arity$1 ? x_scale_12292.cljs$core$IFn$_invoke$arity$1(p2__12289_SHARP_) : x_scale_12292.call(null,p2__12289_SHARP_));
});})(svg_12291,x_scale_12292,y_scale_12293))
).y(y_scale_12293);
svg_12291.append("path").datum(cljs.core.clj__GT_js(thirdpartyjs.core.values)).attr("fill","none").attr("stroke","#3355ee").attr("stroke-width","5px").attr("d",line_12294);
