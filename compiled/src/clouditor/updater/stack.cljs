
(ns clouditor.updater.stack)

(defn define [store op-data]
  (let [pointer (get-in store [:router :pointer])
        next-module-name (get-in store [:stack (inc pointer)])]
    (if (= next-module-name op-data)
      (-> store (update-in [:router :pointer] inc))
      (-> store
       (update
         :stack
         (fn [stack]
           (let [pointer (get-in store [:router :pointer])]
             (conj (subvec stack 0 (inc pointer)) op-data))))
       (update-in [:router :pointer] inc)))))

(defn display [store op-data]
  (-> store
   (assoc :stack [op-data])
   (assoc-in [:router :pointer] 0)
   (assoc-in [:router :page] :editor)))

(defn display-stack [store op-data]
  (-> store
   (assoc :stack op-data)
   (assoc-in [:router :pointer] (dec (count op-data)))
   (assoc-in [:router :page] :editor)))
