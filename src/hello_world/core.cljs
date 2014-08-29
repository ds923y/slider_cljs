(ns hello-world.core
  (:use [hello-world.press :only [press]]
    [hello-world.facts :only [facts]]
    [hello-world.brands :only [brands]]
    [hello-world.intro :only [intro]]
    [hello-world.signin :only [signin]]
    [hello-world.topsection :only [top-section]])
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om  :include-macros true]
            [om.dom  :as dom :include-macros true]
            [goox.ui.Slider]
            [cljs.core.async :refer [put! chan <!]]))

(enable-console-print!)
#_(def css-trans-group (-> js/React (aget "addons") (aget "CSSTransitionGroup")))
#_(def ctg (aget js/React "addons" "CSSTransitionGroup"))



(defn main-view [app owner]
  (reify
  om/IInitState
    (init-state [_] {:js-chan (chan) :ctrl-chan (chan)})
  om/IWillMount
    (will-mount [_]
      (let [js-chan   (om/get-state owner :js-chan)
            ctrl-chan (om/get-state owner :ctrl-chan)]
        (let [my-slider (js/goox.ui.Slider. js-chan put! {:pos 0 :element 0 :display "" :elm-on -1} assoc keyword)]

          (go (loop []
            (let [{:keys [pos element display elm-on]} (<! js-chan)]
              (om/update! app [:press :elm-positions element] pos)
              (om/update! app [:press :elm-display   element] display)
              (om/update! app [:press :elm-on] elm-on)
          (recur))))
          (go (loop []
            (let [forced-slide (<! ctrl-chan)]
              (.force my-slider forced-slide))
          (recur))) )))
  om/IRenderState
  (render-state [this {:keys [ctrl-chan]}]
    (dom/div nil
      #_(dom/div #js {:id "body-bg" :className "clearfix"}
        (dom/div #js {:className "nav-container"}
          (dom/div #js {:id "nav"}
            (dom/ul  nil)
            (dom/div #js {:className "clear"}))
        )
        (dom/div #js {:className "bread-crumb"}
          (dom/div #js {:id "bread-display"}
            (dom/div nil "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" ))
        ))
      (dom/div #js {:className "center-block"}
        (om/build top-section  app)
        (om/build signin app)
        (dom/div  #js {:className "clear"})
        (om/build intro  app)
        (om/build brands app)
        (om/build facts  app)
        (om/build press (:press app) {:init-state {:ctrl-chan ctrl-chan}})
        (dom/div  #js {:className "login-section" :id "tools"}
          (dom/div #js {:className "login-section-header"} (dom/span #js {:className "style"} "developers ") (dom/span nil "tools"))
          (dom/div #js {:className "rstyle-dev-logo"})
          (dom/div #js {:className "dev-content"}
            (dom/a   #js {:href "https://api.rewardstyle.com/"} "API.REWARDSTYLE.COM")
            (dom/div #js {:className "dev1"} (dom/span #js {:className "style"} "[") "advanced developer tools" (dom/span #js {:className "style"} "]"))
            (dom/br  nil)
            (dom/div #js {:className "dev2"} "The only limit is your imagination. Build with us."))
        )
        (dom/div #js {:className "login-section" :id "map"}
          (dom/div #js {:className "map-hover-state"})
        ))))))



#_(def ctg (aget js/React "addons" "CSSTransitionGroup"))
#_(def app-state (atom ["test"]))
#_(defn app [data owner]
(reify
om/IRender
(render [_]
(dom/div nil
(ctg (clj->js {:transitionName "example"
:children (map #(dom/h1 nil %) data)}))
(dom/button #js {:onClick #(om/transact! data (fn [v] (conj v "test")))} "+")
(dom/button #js {:onClick #(om/transact! data pop)} "-")))))
#_(om/root
app
app-state
{:target (. js/document (getElementById "body"))})

(def app-state (atom {
 :press {
   :elm-positions  [-1000, 0, 1000]
   :elm-on 0
   :elm-display ["block", "block", "none"]}}))

(om/root
  main-view
  app-state
    {:target (. js/document (getElementById "body"))})

