
(ns clouditor.component.overview
  (:require [respo.alias :refer [create-comp div]]
            [respo.component.text :refer [comp-text]]
            [respo.component.space :refer [comp-space]]
            [clouditor.style.widget :as widget]
            [clouditor.style.layout :as layout]
            [clouditor.style.typeset :as typeset]
            [clouditor.util.compiler :refer [build-graph
                                             get-unused-names]]
            [clouditor.component.deps-graph :refer [comp-deps-graph]]))

(defn on-display [module-name]
  (fn [e dispatch! mutate!] (dispatch! :stack/display module-name)))

(defn render [modules]
  (fn [state mutate!]
    (div
      {}
      (div {:style typeset/section-title} (comp-text "Graph" nil))
      (let [graph (build-graph modules)]
        (div {} (comp-deps-graph graph [])))
      (div {:style typeset/section-title} (comp-text "Unused" nil))
      (div
        {}
        (let [unused-modules (get-unused-names modules)]
          (if (empty? unused-modules)
            (comp-text "All modules are in the tree." typeset/hint)
            (->>
              unused-modules
              (map
                (fn [module-name] [module-name
                                   (div
                                     {:style widget/module-link,
                                      :event
                                      {:click
                                       (on-display module-name)}}
                                     (comp-text
                                       module-name
                                       nil))])))))))))

(def comp-overview (create-comp :overview render))
