import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";

import HeaderBar from "./Components/Pages/HeaderBar/HeaderBar";
import { Project } from "./Components/Pages/Project/Project";
import { Issue } from "./Components/Pages/Issue/Issue";
import { Home } from "./Components/Pages/Home/Home";

function App() {
  return (
    <div>
      <BrowserRouter>
      <HeaderBar />
        <Routes>
          <Route path="/projects" element={<Project />} />
          <Route path="/issues" element={<Issue />} />
          <Route path="/" element={<Home />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
