import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = { vus: 10, duration: '10s' };

export default function () {
  const login = http.post('http://localhost:8080/auth/login', JSON.stringify({
    email: 'admin@codexhub.local', password: 'admin123'
  }), { headers: {'Content-Type':'application/json'}});
  const token = JSON.parse(login.body).token;
  const res = http.get('http://localhost:8080/catalog/books?page=0&size=10', {
    headers: { Authorization: `Bearer ${token}` }
  });
  check(res, { 'status 200': r => r.status === 200 });
  sleep(1);
}
