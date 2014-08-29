(ns hello-world.intro
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(defn intro [app owner]
  (reify
  om/IRender
    (render [this]
      (dom/div #js {:className "stripe-border" :id "intro"}
        (dom/div #js {:className "content"}
          (dom/div #js {:className "tagline"} "rewardStyle is an invitation-only web tool that helps top tier style publishers find and monetize their content.")
          (dom/div #js {:className "box"}
            (dom/h3 "DISCOVER")
            (dom/p  "rewardStyle allows<br /> you to search millions<br /> of products from<br /> one location with<br /> just one login")
          )
          (dom/div #js {:className "box"}
            (dom/h3 "LINK")
            (dom/p  "Enrich your users'<br /> experience with<br /> relevant shopping<br /> links added<br /> to your posts")
          )
          (dom/div #js {:className "box"}
            (dom/h3 "EARN")
            (dom/p  "When users click on a<br/> link & make a purchase,<br/> you earn a commission<br/>&mdash;check our centralized<br/> reporting to see what your<br/> users click on and buy")
          )
          (dom/div #js {:className "box"}
            (dom/h3 "GROW")
            (dom/p  "Dramatically increase<br/> your blog’s earning<br/> potential with proven<br/> strategies and support<br/> from our team of<br/> web consultants")
          )
          (dom/div #js {:id "advertisor-CTA-arrow"})
          (dom/div #js {:id "advertisor-CTA"})
          (dom/div #js {:id "bloggers-apply-arrow"})
          (dom/div #js {:className "text-center"} (dom/input #js {:type "button" :className "button-pink-lg" :value "Apply for an Invitation"})))))))
