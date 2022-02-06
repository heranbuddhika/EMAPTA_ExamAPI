import React, { useEffect, useState } from "react";
import { DataGrid } from "@mui/x-data-grid";
import axios from "axios";
import CreateProjectForm from "./CreateProjectForm";

const columns = [
  { field: "projectId", headerName: "ID", width: 70 },
  { field: "projectName", headerName: "Project name", width: 130 },
];

export const Project = () => {
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    axios
      .get(`/exam-api/projects`, {
        auth: {
          username: "admin",
          password: "password",
        },
      })
      .then((response) => {
        setProjects(response.data);
      });
  }, []);

  return (
    <>
      <div className="container mt-4">
        <h3>Projects List</h3>
        <p>
          Lorem, ipsum dolor sit amet consectetur adipisicing elit. Consectetur
          obcaecati hic reiciendis eius, srecusandae ducimus voluptate odio
          sapiente nisi culpa quas, voluptas at, fuga distinctio iste illo
          dolorum voluptatem unde.
        </p>
        <div style={{ height: 400, width: "100%" }}>
          <DataGrid
            getRowId={(projects) => projects.projectId}
            rows={projects}
            columns={columns}
            pageSize={5}
            rowsPerPageOptions={[5]}
            checkboxSelection
          />
        </div>
      </div>
      <CreateProjectForm />
    </>
  );
};
