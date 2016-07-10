
(ns clouditor.updater.tree)

(defn get-module-name [store]
  (let [router (:router store)
        stack (:stack store)
        modules (:modules store)
        pointer (:pointer router)]
    (get stack pointer)))

(defn get-coord [store] (get-in store [:router :coord]))

(defn expr-append [store op-data]
  (let [coord (get-coord store)
        module-name (get-module-name store)
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

(defn expr-prepend [store op-data] store)

(defn before [store op-data]
  (let [coord (get-coord store)
        module-name (get-module-name store)
        transform (fn [expression]
                    (into
                      []
                      (let [pos (last coord)]
                        (cond
                          (= pos 0) (cons "" expression)
                          :else (concat
                                  (subvec expression 0 pos)
                                  [""]
                                  (subvec expression pos))))))]
    (-> store
     (update-in
       [:modules module-name]
       (fn [tree]
         (cond
           (= (count coord) 1) (transform tree)
           (> (count coord) 1) (update-in
                                 tree
                                 (butlast coord)
                                 transform)
           :else tree))))))

(defn after [store op-data]
  (let [coord (get-coord store)
        module-name (get-module-name store)
        transform (fn [expression]
                    (into
                      []
                      (let [pos (last coord)]
                        (cond
                          (= pos (dec (count expression))) (conj
                                                             expression
                                                             "")
                          :else (concat
                                  (subvec expression 0 (inc pos))
                                  [""]
                                  (subvec expression (inc pos)))))))]
    (-> store
     (update-in
       [:modules module-name]
       (fn [tree]
         (cond
           (= (count coord) 1) (transform tree)
           (> (count coord) 1) (update-in
                                 tree
                                 (butlast coord)
                                 transform)
           :else tree))))))

(defn rm [store op-data]
  (let [coord (get-coord store)
        module-name (get-module-name store)
        transform (fn [expression]
                    (into
                      []
                      (let [pos (last coord)]
                        (cond
                          (= pos 0) (rest expression)
                          (= pos (dec (count expression))) (butlast
                                                             expression)
                          :else (concat
                                  (subvec expression 0 pos)
                                  (get expression pos)
                                  (subvec expression (inc pos)))))))]
    (-> store
     (update-in
       [:modules module-name]
       (fn [tree]
         (cond
           (= (count coord) 1) (transform tree)
           (> (count coord) 1) (update-in
                                 tree
                                 (butlast coord)
                                 transform)
           :else tree))))))

(defn fold [store op-data]
  (let [coord (get-in store [:router :coord])
        module-name (get-module-name store)]
    (-> store
     (update-in
       [:modules module-name]
       (fn [tree] (update-in tree coord (fn [item] [item])))))))

(defn expr-unfold [store op-data]
  (let [coord (get-coord store)
        module-name (get-module-name store)
        transform (fn [expression]
                    (into
                      []
                      (let [pos (last coord)]
                        (cond
                          (= pos 0) (concat
                                      (first expression)
                                      (rest expression))
                          (= pos (dec (count expression))) (concat
                                                             (butlast
                                                               expression)
                                                             (last
                                                               expression))
                          :else (concat
                                  (subvec expression 0 pos)
                                  (get expression pos)
                                  (subvec expression (inc pos)))))))]
    (cond
      (= (count coord) 1) (-> store
                           (update-in
                             [:modules module-name]
                             (fn [tree] (transform tree))))
      (> (count coord) 1) (-> store
                           (update-in
                             [:modules module-name]
                             (fn [tree]
                               (update-in
                                 tree
                                 (butlast coord)
                                 transform))))
      :else store)))

(defn token-modify [store op-data]
  (let [coord (get-in store [:router :coord])
        module-name (get-module-name store)]
    (-> store
     (update-in
       [:modules module-name]
       (fn [tree] (assoc-in tree coord op-data))))))

(defn focus [store op-data] (assoc-in store [:router :coord] op-data))

(defn reset [store op-data]
  (let [module-name (get-module-name store)]
    (-> store
     (update :modules (fn [modules] (dissoc modules module-name))))))
