
(ns clouditor.component.expression
  (:require [respo.alias :refer [create-comp div]]
            [clouditor.style.layout :as layout]
            [clouditor.style.widget :as widget]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.component.expression-toolbar :refer [comp-expression-toolbar]]
            [clouditor.component.token :refer [comp-token]]
            [clouditor.util.detect :refer [deep?]]))

(declare comp-expression)

(defn on-focus [coord]
  (fn [e dispatch! mutate!] (dispatch! :tree/focus coord)))

(defn render [expression coord focused at-tail?]
  (fn [state mutate!]
    (let [last-index (dec (count expression))]
      (div
        {:style
         (merge
           widget/expression-box
           (if (deep? expression) {:display "block"})
           (if at-tail? {:display "inline-block"}))}
        (div
          {:style widget/expression,
           :event {:click (on-focus coord)},
           :attrs {:tab-index 0}}
          (->>
            expression
            (map-indexed
              (fn [index item]
                (let [child-coord (conj coord index)
                      tail? (= index last-index)]
                  [index
                   (if (string? item)
                     (comp-token item child-coord focused)
                     (comp-expression
                       item
                       child-coord
                       focused
                       tail?))])))))
        (if (= coord focused) (comp-expression-toolbar))))))

(def comp-expression (create-comp :expression render))
