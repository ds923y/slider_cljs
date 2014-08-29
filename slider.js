/**
 * @fileoverview A content slider.
 */

// TODO: emit events.

goog.provide("goox.ui.Slider");
goog.require("goog.events");
goog.require("goog.dom");
goog.require("goog.style");
goog.require("goog.math");
goog.require("goog.fx");
goog.require("goog.fx.dom");
goog.require('my.om.MyOmSlide');
goog.require("goog.fx.AnimationParallelQueue");

/**
 * Creates an animation object that will slide an element from A to B.
 *
 * @param @const {Function=} put
 * @param {Function=} chan
 * @param @const {!Object}  obj
 * @param @const {Function=} assoc
 * @param @const {Function=} keyword
 * @constructor
 */
goox.ui.Slider = function (chan, put, obj, assoc, keyword) {
        this.forceTime  = function(e){return e};
        this.forceOrder = false;

        this.timer = new goog.Timer(5000); //when the time in this object expires the attached event listener will perform a new animation cycle
        this.order = [0, 1, 2];            //The slide number maintained as a queue.
        var self = this;

        this.timer.addEventListener(goog.Timer.TICK, function(e) {
          var trans_time,  //Will be used dictate the transition time of the animation
              new_left,    //specifies the "left" css style at the end of the trans_time
              old_left,    //specifies the "left" css style at the begining of the animation
              new_active,  //the next active-dot
              slide,       //The currently processing HTMLDomElement that represents the slide on the page
              animations,  //The closure librarys animation object.
              display;     //Will this element display

          new_active = -1*(self.order.length - self.order.indexOf(0))%3 + 2;
          animations = new goog.fx.AnimationParallelQueue();
          for(var i = 0; i < self.order.length || self.forceOrder; i++){
            if(i == 0){
              trans_time = 0;
              display    = "none";
            }else{
              trans_time = self.forceTime(1000);
              display    = "block";
            }


            new_left   = -1*(self.order.length - i)%3*1000 + 1000;
            old_left   = (i*1000 - 1000);

            animations.add(new my.om.MyOmSlide(null, [old_left, 0], [new_left, 0], trans_time, goog.fx.easing.easeOut,
                                                put, chan, obj, assoc, keyword, self.order[i], display, new_active));

          }
          animations.play();

          //prepare for the next iteration.
          self.order.push(self.order.shift());
          goog.events.listen(animations, goog.fx.Animation.EventType.END, function(e){
            animations.dispose();
          }, false, this);
        });
        this.timer.start();

}

goox.ui.Slider.prototype.force = function(chosen){
  this.timer.stop();
  this.forceTime  = function(e){return 200;};

  var self = this;
  function recur(){
    if(self.order[chosen] == 0){
      return;
    }else{
      self.timer.dispatchTick();
      setTimeout(function(){recur()}, 200);
    }
  }
  recur();
  this.forceTime  = function(e){return e};
  this.timer.start();
}
