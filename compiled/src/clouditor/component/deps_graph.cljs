
(ns clouditor.component.deps-graph
  (:require [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [clouditor.style.widget :as widget]
            [clouditor.style.layout :as layout]))

(declare comp-deps-graph)

(defn render [graph]
  (fn [state mutate!]
    (div
      {}
      (->>
        graph
        (map
          (fn [entry]
            (let [module-name (key entry) deps (val entry)]
              [module-name
               (div
                 {:style layout/row}
                 (div {} (comp-text module-name widget/module-link))
                 (comp-deps-graph deps))])))))))

(def comp-deps-graph (create-comp :deps-graph render))
