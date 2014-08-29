(ns hello-world.facts
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(defn facts [app owner]
  (reify
    om/IRender
    (render [this]
           (dom/div #js {:className "login-section" :id "facts"}
             (dom/div #js {:className "login-section-header"} "get the facts")
             (dom/div #js {:className "facts-content"}
               (dom/div #js {:className "column" :id "facts-content-left"}
                 (dom/div #js {:id "facts-content-left-top"}  (dom/span nil "2500") (dom/br nil) (dom/span #js {:className "small"} "retailers") )
                 (dom/div #js {:id "facts-content-left-middle"} (dom/span nil "200K") (dom/br nil) (dom/span #js {:className "small"} "brands") )
                 (dom/div #js {:id "facts-content-left-bottom"} (dom/span nil "130") (dom/br nil) (dom/span #js {:className "small"} "countries") )
               )
               (dom/div #js {:className "column" :id "facts-content-middle"}
                 (dom/div #js {:id "facts-content-middle-top"}
                   (dom/div nil "a global monetization solution that is 100% built for top tier digital style publishers")
                   (dom/br nil)
                   "rewardStyle has built a suite of tools that allow top tier style publishers to monetize their entire digital brand"
                 )
                 (dom/div #js {:id "facts-content-middle-bottom"} (dom/span #js {:className "small"} "fashion") (dom/br nil) "from proenza schouler & alexander wang to asos & topshop" (dom/br nil) (dom/span #js {:className "small"} "beauty") (dom/br nil) "from ysl & clinique to l'oreal & sephora" (dom/br nil) (dom/span #js {:className "small"} "lifestyle") (dom/br nil) "from west elm & pottery barn to target & fab")
               )
               (dom/div #js {:className "column" :id "facts-content-right"}
                 (dom/span nil "access to")
                 (dom/div nil "fashion")
                 (dom/div nil "beauty")
                 (dom/div nil (dom/span #js {:className "style"} "&") " lifestyle")
                 (dom/div nil "retailers")
                 (dom/span nil "around the world")
               )
             )
          )

)))
