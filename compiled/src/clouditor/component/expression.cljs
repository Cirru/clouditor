
(ns clouditor.component.expression
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.component.expression-toolbar :refer [comp-expression-toolbar]]
            [clouditor.component.token :refer [comp-token]]))

(declare comp-expression)

(defn on-focus [coord]
  (fn [e dispatch! mutate!] (dispatch! :tree/focus coord)))

(defn render [expression coord focused]
  (fn [state mutate!]
    (div
      {:style widget/expression}
      (div
        {:style widget/expression,
         :event {:click (on-focus coord)},
         :attrs {:tab-index 0}}
        (->>
          expression
          (map-indexed
            (fn [index item]
              (let [child-coord (conj coord index)]
                [index
                 (if (string? item)
                   (comp-token item child-coord focused)
                   (comp-expression item child-coord focused))])))))
      (if (= coord focused) (comp-expression-toolbar)))))

(def comp-expression (create-comp :expression render))
