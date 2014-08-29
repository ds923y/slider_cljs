(ns hello-world.brands
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(defn brands [app owner]
  (reify
  om/IRender
    (render [this]
      (dom/div #js {:className "login-section" :id "brands"}
        (dom/div #js {:className "login-section-header"} "brands")
        (dom/div #js {:id "brands_slider"}
          (dom/div #js {:className "brands-slideshow swipe-wrap"}
            (dom/div #js {:id "Group1"  :className "page"})
            (dom/div #js {:id "Group2"  :className "page"})
            (dom/div #js {:id "Group3"  :className "page"})
            (dom/div #js {:id "Group4"  :className "page"})
            (dom/div #js {:id "Group5"  :className "page"})
            (dom/div #js {:id "Group6"  :className "page"})
            (dom/div #js {:id "Group7"  :className "page"})
            (dom/div #js {:id "Group8"  :className "page"})
            (dom/div #js {:id "Group9"  :className "page"})
            (dom/div #js {:id "Group10" :className "page"})
            (dom/div #js {:id "Group11" :className "page"})
            (dom/div #js {:id "Group12" :className "page"})
          )
          (dom/div #js {:className "clear"})
          (dom/div #js {:className "brands-scroll-dots"}
            (dom/div #js {:id "brands-dot1"  :className "active-dot"})
            (dom/div #js {:id "brands-dot2"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot3"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot4"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot5"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot6"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot7"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot8"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot9"  :className "inactive-dot"})
            (dom/div #js {:id "brands-dot10" :className "inactive-dot"})
            (dom/div #js {:id "brands-dot11" :className "inactive-dot"})
            (dom/div #js {:id "brands-dot12" :className "inactive-dot"})
          ))))))
