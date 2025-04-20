## 🧩 **Phase 1: Prerequisites (1–2 weeks)**

### 🛠️ Skills to Learn

- **Linux basics**: Shell, file system, networking
    
- **Docker**: Build, run, and manage containers
    
- **Basic YAML**: Syntax and formatting
    

### 🔗 Resources

- Docker Official Docs
    
- [Linux Journey](https://linuxjourney.com/)
    
- YAML Tutorial
    

---

## 🚀 **Phase 2: Kubernetes Basics (2–3 weeks)**

### 🛠️ Skills to Learn

- What is Kubernetes? Architecture & components
    
    - Master: API Server, Scheduler, Controller Manager
        
    - Worker: kubelet, kube-proxy
        
- Pods, ReplicaSets, Deployments
    
- Services (ClusterIP, NodePort, LoadBalancer)
    
- Namespaces
    
- ConfigMaps & Secrets
    
- Volumes & Persistent Volumes
    

### 🔗 Resources

- Kubernetes Official Docs - Concepts
    
- [Kubernetes by Example](https://kubernetesbyexample.com/)
    
- [Kelsey Hightower's Kubernetes The Hard Way](https://github.com/kelseyhightower/kubernetes-the-hard-way) (for later reference)
    

---

## 🧪 **Phase 3: Hands-On Practice (2–3 weeks, ongoing)**

### 🛠️ Tools to Use

- **Minikube** / **kind** (Kubernetes IN Docker)
    
- **kubectl** (CLI tool for Kubernetes)
    
- **Lens** (GUI for managing clusters)
    
- **Play with Kubernetes**: https://labs.play-with-k8s.com
    

### 🧑‍💻 Exercises

- Deploy a simple Node.js or Python app
    
- Scale deployments
    
- Use ConfigMaps & Secrets
    
- Set up service discovery
    

---

## 🔧 **Phase 4: Intermediate Concepts (3–4 weeks)**

### 🛠️ Topics to Learn

- Ingress Controllers (e.g., NGINX Ingress)
    
- Network Policies
    
- Resource Limits & Requests
    
- Probes: Liveness & Readiness
    
- StatefulSets
    
- Jobs & CronJobs
    
- Helm (package manager for K8s)
    

### 🔗 Resources

- NGINX Ingress Docs
    
- Helm Official Docs
    

---

## 🧱 **Phase 5: Advanced Kubernetes (4+ weeks)**

### 🛠️ Topics to Learn

- Custom Resource Definitions (CRDs)
    
- Operators & Controllers
    
- Admission Controllers
    
- Kubernetes API Extensions
    
- Multi-cluster management
    
- Kubernetes Federation
    
- Performance tuning & autoscaling (HPA, VPA, Cluster Autoscaler)
    
- Security: RBAC, PodSecurityPolicies, NetworkPolicy, Secrets Encryption
    

### 🔧 Tools

- **Kustomize** (for advanced configuration management)
    
- **Prometheus + Grafana** (Monitoring)
    
- **Fluentd / Loki / ELK** (Logging)
    
- **OPA / Kyverno** (Policy enforcement)
    

### 🔗 Resources

- Kubernetes Patterns Book
    
- [Learnk8s](https://learnk8s.io/)
    
- CNCF Glossary
    

---

## 🧠 **Phase 6: Certifications (Optional but Valuable)**

- **CKA** (Certified Kubernetes Administrator)
    
- **CKAD** (Certified Kubernetes Application Developer)
    
- **Tips**:
    
    - Use killer.sh practice tests
        
    - Time yourself with mock exams
        

---

## 🧪 Project Ideas (Hands-On)

- Deploy a CI/CD pipeline with Jenkins in K8s
    
- Build a scalable e-commerce microservice app with Helm charts
    
- Implement GitOps with ArgoCD or FluxCD
    
- Set up observability stack (Prometheus + Grafana)
    

---

## 🧰 Useful Tools Throughout

- **kubectl**, **k9s**, **Lens**
    
- **Helm**, **Kustomize**
    
- **Minikube**, **kind**, **Docker Desktop**
    
- **VSCode with Kubernetes extension**