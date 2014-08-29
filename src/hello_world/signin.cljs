(ns hello-world.signin
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]
            [cljs.core.async :refer [put! chan <!]]))

(defn signin [app owner]
  (reify
    om/IRender
    (render [this]
(dom/input #js {:type "hidden" :id "get-user-lang" :name "user-lang" :value "en"}
          (dom/div #js {:id "login" :className "clearfix"}
            (dom/div #js {:className "signin"}
              (dom/div  #js {:id "signin-header"})
              (dom/form #js {:action "https://www.rewardstyle.com/?language=1" :method "post"}

                  (dom/div #js {:className "inputs"}
                    (dom/div #js {:className "pair"}
                      (dom/label #js {:className "text email_icon"} )
                      (dom/input #js {:id "email" :type "text" :className "text" :name "email" :placeholder "email"})
                      (dom/div #js {:className "clear"})
                      (dom/div #js {:className "pair"}
                        (dom/label #js {:className "text password_icon"} )
                        (dom/input #js {:id "password" :type "password" :className "text" :name "password" :placeholder "password"})
                        (dom/input #js {:type "hidden" :name "HTTP_REFERER" :value ""})
                        (dom/input #js {:type "hidden" :name "HTTP_ORIGIN" :value ""})
                        (dom/input #js {:type "hidden" :name "r" :value ""})
                        (dom/input #js {:type "hidden" :name "source" :value "login_page"})
                        (dom/input #js {:type "checkbox" :className "checkbox" :name "remember" :id "login_remember"})
                        (dom/label #js {:className "checkbox" :for "login_remember"} "Remember Me")
                        (dom/div #js {:className "clear"})
                      )
                      (dom/div #js {:className "login-buttons"}
                        (dom/input #js {:className "button gray-striped" :type "button" :value "apply"})
                        (dom/input #js {:type "submit" :className "button black" :name "login" :value "LOG IN"})
                        (dom/div #js {:className "clear"})
                      )
                    )
                  )


 )))))))
