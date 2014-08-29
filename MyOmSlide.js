goog.provide("my.om.MyOmSlide");
goog.require("goog.math");
goog.require("goog.fx");
goog.require("goog.fx.dom");
goog.require('goog.fx.dom.Slide');

/**
 * Creates an animation object that will slide an element from A to B.  (This
 * in effect automatically sets up the onanimate event for an Animation object)
 *
 * Start and End should be 2 dimensional arrays
 *
 * @param {Element} element Dom Node to be used in the animation.
 * @param {Array.<number>} start 2D array for start coordinates (X, Y).
 * @param {Array.<number>} end 2D array for end coordinates (X, Y).
 * @param {number} time Length of animation in milliseconds.
 * @param {Function=} opt_acc Acceleration function, returns 0-1 for inputs 0-1.
 * @param @const {Function=} put
 * @param {Function=} chan
 * @param @const {!Object}  obj
 * @param @const {Function=} assoc
 * @param @const {Function=} keyword
 * @param @const {number} elm_pos
 * @param @const {number} idx
 * @extends {goog.fx.dom.PredefinedEffect}
 * @constructor
 */
my.om.MyOmSlide = function(element, start, end, time, opt_acc, put, chan, obj, assoc, keyword, elm_pos, display, index_on) {
  var new1 = assoc(obj , keyword("element"), elm_pos);
  var new2 = assoc(new1, keyword("display"), display);
  this.obj = assoc(new2, keyword("elm-on"), index_on);
  this.put = put;
  this.chan = chan;
  this.assoc = assoc;
  this.keyword  = keyword;
  goog.fx.dom.Slide.apply(this, arguments);
}

goog.inherits(my.om.MyOmSlide, goog.fx.dom.Slide);

/** @override */
my.om.MyOmSlide.prototype.updateStyle = function() {
  var new1 = this.assoc(this.obj, this.keyword("pos"), Math.round(this.coords[0]));
  this.put(this.chan, new1);
}
