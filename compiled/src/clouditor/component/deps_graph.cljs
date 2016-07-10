
(ns clouditor.component.deps-graph
  (:require [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [clouditor.style.widget :as widget]
            [clouditor.style.layout :as layout]))

(defn on-display-stack [stack]
  (fn [e dispatch! mutate!] (dispatch! :stack/display-stack stack)))

(declare comp-deps-graph)

(defn render [graph stack]
  (fn [state mutate!]
    (div
      {:style (merge layout/row widget/graph-hinter)}
      (->>
        graph
        (map
          (fn [entry]
            (let [module-name (key entry)
                  deps (val entry)
                  new-stack (conj stack module-name)]
              [module-name
               (div
                 {:style layout/column}
                 (div
                   {:event {:click (on-display-stack new-stack)}}
                   (comp-text module-name widget/module-link))
                 (comp-deps-graph deps new-stack))])))))))

(def comp-deps-graph (create-comp :deps-graph render))
