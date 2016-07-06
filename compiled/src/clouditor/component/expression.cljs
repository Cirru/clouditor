
(ns clouditor.component.expression
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]))

(declare comp-expression)

(defn render [] (fn [state mutate!] (div {:style widget/expression})))

(def comp-expression (create-comp :expression render))
