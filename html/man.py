import numpy as np
import scipy
from matplotlib import pyplot as plt

X_ = np.array([2, 11, 5, 1, 7])
X = X_[:,None]
X
#D = scipy.spatial.distance.pdist(X, 'cityblock')
D = np.array([ 10.,  8.,  7.,  8.,  4., 2.,  5.,  8.,  7.,  3.])
scipy.spatial.distance.squareform(D)
Z = scipy.cluster.hierarchy.linkage(D, 'complete')
Z
fig, ax = plt.subplots(dpi=100)
dn = scipy.cluster.hierarchy.dendrogram(Z, labels=list(X_), ax=ax)
ax.set_ylabel("Distance")
plt.show()