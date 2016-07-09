
(ns clouditor.updater.router)

(defn point [store op-data]
  (-> store
   (assoc-in [:router :pointer] op-data)
   (assoc-in [:router :page] :editor)))

(defn page [store op-data]
  (-> store (assoc-in [:router :page] op-data)))
