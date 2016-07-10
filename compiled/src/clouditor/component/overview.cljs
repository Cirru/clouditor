
(ns clouditor.component.overview
  (:require [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.style.widget :as widget]
            [clouditor.style.layout :as layout]
            [clouditor.util.compiler :refer [build-graph]]
            [clouditor.component.deps-graph :refer [comp-deps-graph]]))

(defn render [modules]
  (fn [state mutate!]
    (div
      {}
      (let [graph (build-graph modules)]
        (div {} (comp-deps-graph graph)))
      (comment
        div
        {}
        (->>
          modules
          (map-indexed
            (fn [index entry] [index
                               (div
                                 {:style
                                  (merge layout/row widget/data-row)}
                                 (div
                                   {:style widget/module-name}
                                   (comp-text
                                     (first entry)
                                     (widget/module-link)))
                                 (div
                                   {:style widget/code}
                                   (comp-text (last entry))))])))))))

(def comp-overview (create-comp :overview render))
