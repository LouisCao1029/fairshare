import { useState } from 'react';
import './App.css';

// Constants
const countries = [
  { name: 'New Zealand', currency: 'NZD' },
  { name: 'Australia', currency: 'AUD' }
];

const currencies = [
  'NZD',
  'AUD'
];

// App Component
function App() {
  const [age, setAge] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [country, setCountry] = useState('');
  const [currency, setCurrency] = useState('');
  const [errors, setErrors] = useState({});

  function handleCountryChange(event) {
    const selectedCountry = event.target.value;
    setCountry(selectedCountry);

    const countryData = countries.find(
      (c) => c.name === selectedCountry
    );

    if (countryData) {
      setCurrency(countryData.currency);
    }
  }

  function validate() {
    const newErrors = {};

    if (!age) {
      newErrors.age = 'Age is required';
    } else if (age < 0 || age > 100) {
      newErrors.age = 'Age must be between 0 and 100';
    }

    if (!email) {
      newErrors.email = 'Email is required';
    } else if (!/\S+@\S+\.\S+/.test(email)) {
      newErrors.email = 'Email is invalid';
    }

    if (!password) {
      newErrors.password = 'Password is required';
    } else if (password.length < 8) {
      newErrors.password = 'Password must be at least 8 characters';
    }

    if (!country) {
      newErrors.country = 'Country is required';
    }

    if (!currency) {
      newErrors.currency = 'Currency is required';
    }

    return newErrors;
  }

  // Handle form submission
  function handleSubmit(event) {
    event.preventDefault();
    const validationErrors = validate();
    setErrors(validationErrors);

    if (Object.keys(validationErrors).length === 0) {
      console.log('Form submitted successfully:', { age, email, password, country, currency });
    }
  }

  return (
    <div className="page">
      <div className="profile-card">
        <h1>Create Your Profile</h1>
        <p className="subtitle">Enter your information below</p>

        {/* FIX 1: Changed outer div to <form> and attached onSubmit */}
        <form onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="age">Age</label>
            <input
              id="age"
              type="number"
              value={age}
              placeholder="Enter your age"
              onChange={(event) => setAge(event.target.value)}
            />
            {errors.age && <span className="error">{errors.age}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="email">Email</label>
            <input
              id="email"
              type="email"
              value={email}
              placeholder="Enter your email"
              onChange={(event) => setEmail(event.target.value)}
            />
            {errors.email && <span className="error">{errors.email}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="password">Password</label>
            <input
              id="password"
              type="password"
              value={password}
              placeholder="Enter your password"
              onChange={(event) => setPassword(event.target.value)}
            />
            {errors.password && <span className="error">{errors.password}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="country">Country</label>
            <select id="country" value={country} onChange={handleCountryChange}>
              <option value="">Select your country</option>
              {countries.map((c) => (
                <option key={c.name} value={c.name}>
                  {c.name}
                </option>
              ))}
            </select>
            {errors.country && <span className="error">{errors.country}</span>}
          </div>

          <div className="form-group">
            <label htmlFor="currency">Currency</label>
            <select
              id="currency"
              value={currency}
              onChange={(event) => setCurrency(event.target.value)}
            >
              <option value="">Select your currency</option>
              {currencies.map((curr) => (
                <option key={curr} value={curr}>
                  {curr}
                </option>
              ))}
            </select>
            {errors.currency && <span className="error">{errors.currency}</span>}
          </div>

          <button type="submit">Create Profile</button>
        </form> {/* FIX 1: Matching closing form tag */}
        
      </div>
    </div>
  );
}

export default App;
