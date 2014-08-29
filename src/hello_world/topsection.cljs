(ns hello-world.topsection
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
    [om.dom :as dom :include-macros true]
    [cljs.core.async :refer [put! chan <!]]))

(defn top-section [app owner]
  (reify
  om/IRender
    (render [this]
      (dom/div #js {:className "top-section"}
        (dom/div #js {:id "language-select-area"}
          (dom/input #js {:type "checkbox" :name "dropdown"})
          (dom/div   #js {:className "language-header"} "English" (dom/div nil))
          (dom/ul    #js {:id "language-select" :name "language"}
            (dom/li #js {:name "English" :value "en" :data-lang "1" :data-lang-id ""} "Any" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "English" :value "en" :data-lang "1" :data-lang-id ""} (dom/span #js {:className "lang-lable"} "English") #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "French - République française" :value "fr" :data-lang "11" :data-lang-id ""} "French - Français" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Germany - Deutsch" :value "de" :data-lang "13" :data-lang-id ""} "German - Deutsch" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Italian - Italia"  :value "it" :data-lang "12" :data-lang-id ""} "Italian - Italiano" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Japanese - 日本人" :value "jp" :data-lang "18" :data-lang-id ""} "Japanese - 日本語" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Korean - 한국의"   :value "kr" :data-lang "3" :data-lang-id ""} "Korean - 한국의" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Mandarin - 国语"   :value "zh" :data-lang "2" :data-lang-id ""} "Mandarin - 国语" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Portugese - Português" :value "pt" :data-lang "47" :data-lang-id ""} "Portugese - Português" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Russian - Pусский"     :value "ru" :data-lang "19" :data-lang-id ""} "Russian - Pусский" #_(dom/input #js {:type "radio" :name "dropdown"}))
            (dom/li #js {:name "Spanish - Español"     :value "es" :data-lang "14" :data-lang-id ""} "Spanish - Español" #_(dom/input #js {:type "radio" :name "dropdown"}))
          ))
        (dom/div #js {:className "for-brands"}
          (dom/a #js   {:href "https://advertisers.rewardstyle.com/"} (dom/span nil "for brands")))
        (dom/div #js {:className "for-pubs"}
          (dom/a #js   {:href "#"}(dom/span nil "for publishers")))))))
