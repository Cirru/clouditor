
(ns clouditor.updater.tree)

(defn get-module-name [store]
  (let [router (:router store)
        stack (:stack store)
        modules (:modules store)
        pointer (:pointer router)]
    (get stack pointer)))

(defn append [store op-data]
  (let [router (:router store)
        coord (:coord router)
        module-name (get-in store [:stack (:pointer router)])
        modules (:modules store)]
    (if (contains? modules module-name)
      (update-in
        store
        [:modules module-name]
        (fn [tree]
          (if (empty? coord)
            (conj tree "")
            (update-in
              tree
              coord
              (fn [expression] (conj expression ""))))))
      (assoc-in store [:modules module-name] [""]))))

(defn prepend [store op-data] store)

(defn rm [store op-data] store)

(defn fold [store op-data] store)

(defn unfold [store op-data] store)

(defn modify [store op-data]
  (let [coord (get-in store [:router :coord])
        module-name (get-module-name store)]
    (-> store
     (update-in
       [:modules module-name]
       (fn [tree] (assoc-in tree coord op-data))))))

(defn focus [store op-data] (assoc-in store [:router :coord] op-data))
