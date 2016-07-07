
(ns clouditor.component.expression
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.component.expression-toolbar :refer [comp-expression-toolbar]]))

(declare comp-expression)

(defn render []
  (fn [state mutate!]
    (div
      {:style widget/expression}
      (div {:attrs {:tab-index 0}})
      (comp-expression-toolbar))))

(def comp-expression (create-comp :expression render))
