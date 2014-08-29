(ns hello-world.press
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(defn press [app owner]
  (reify
    om/IRenderState
    (render-state [this {:keys [ctrl-chan]}]
    (dom/div #js {:className "login-section" :id "press"}
        (dom/div #js {:className "login-section-header"} "in the press")
        (dom/div #js {:id "press_slider"}
            (dom/div #js {:id "goox.ui.Slider" :className "press-slideshow swipe-wrap"}
                (dom/div #js {:className "page" :style (js-obj "left"     (str (get-in app [:elm-positions 0]) "px")
                                                               "display"  (get-in app [:elm-display 0])
                                                               "position" "absolute")}
                    (dom/div #js {:className "pressName" :id "coveteur"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "“A fashion blogger’s saving-grace tool”")
                    )
                    (dom/div #js {:className "pressName" :id "fashionista"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "“Undeniably the preferred affiliate marketer amongst fashion bloggers”")
                    )
                    (dom/div #js {:className "pressName" :id "teenvogue"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "“Disruptive ... there really is no better way to describe rewardStyle. It's a simple and elegant platform, and the numbers are seriously impressive.”")
                    )
                )
                (dom/div #js {:className "page" :style (js-obj "left"     (str (get-in app [:elm-positions 1]) "px")
                                                               "display"  (get-in app [:elm-display 1])
                                                               "position" "absolute")}
                    (dom/div #js {:className "pressName" :id "neimanmarcus"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "“Their relationships with a diverse group of influential fashion bloggers has introduced our brand to a broader audience beyond our core customer.”")
                    )
                    (dom/div #js {:className "pressName" :id "bagsnob"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "“We’ve been working with affiliates for eight years now, and [they are] the first one who is approaching it from a blogger’s perspective,” “It’s super easy, and [they are] constantly upgrading.”")
                    )
                    (dom/div #js {:className "pressName" :id "techcrunch"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "“[Glossi] has made its first move towards publisher monetization through a partnership with affiliate network rewardStyle.")
                    )
                )
                (dom/div #js {:className "page" :style (js-obj "left"     (str (get-in app [:elm-positions 2]) "px")
                                                               "display"  (get-in app [:elm-display 2])
                                                               "position" "absolute")}
                    (dom/div #js {:className "pressName" :id "wwd"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "“As easy as clicking a button”")
                    )
                    (dom/div #js {:className "pressName" :id "tzr"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "Every blogger’s best friend")
                    )
                    (dom/div #js {:className "pressName" :id "mashable"}
                        (dom/div #js {:className "presslogo"})
                        (dom/div #js {:className "press-description"} "A new legitimate way to monetize on social media.")
                    )
                )
            )
            (dom/div #js {:className "clear"})
            (apply dom/div #js {:className "press-scroll-dots"}
              (let [f #(if (= (:elm-on app) %) "active-dot" "inactive-dot")]
                (list
                  (dom/div #js {:id "press-dot1" :className (f 0) :onClick #(put! ctrl-chan 0)})
                  (dom/div #js {:id "press-dot2" :className (f 1) :onClick #(put! ctrl-chan 1)})
                  (dom/div #js {:id "press-dot3" :className (f 2) :onClick #(put! ctrl-chan 2)})))
            )
        )
    )


                  )))
