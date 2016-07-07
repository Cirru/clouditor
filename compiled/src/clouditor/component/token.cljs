
(ns clouditor.component.token
  (:require [respo.alias :refer [create-comp div input]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [clouditor.util.measure :refer [text-width]]
            [clouditor.component.token-toolbar :refer [comp-token-toolbar]]))

(defn on-modify [e dispatch! mutate!]
  (dispatch! :tree/modify (:value e)))

(defn on-focus [coord]
  (fn [e dispatch! mutate!]
    (println "focus!")
    (dispatch! :tree/focus coord)))

(defn render [token coord focused]
  (fn [state mutate!]
    (let [w (+ 12 (text-width token 14 "Source Code Pro"))]
      (div
        {:style widget/token-box}
        (input
          {:style (merge widget/token {:width (str w "px")}),
           :event {:click (on-focus coord), :input on-modify}})
        (if (= coord focused) (comp-token-toolbar))))))

(def comp-token (create-comp :token render))
