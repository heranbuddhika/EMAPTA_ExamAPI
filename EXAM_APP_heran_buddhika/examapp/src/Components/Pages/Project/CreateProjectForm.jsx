import * as React from "react";
import Button from "@mui/material/Button";
import TextField from "@mui/material/TextField";
import Dialog from "@mui/material/Dialog";
import DialogActions from "@mui/material/DialogActions";
import DialogContent from "@mui/material/DialogContent";
import DialogContentText from "@mui/material/DialogContentText";
import DialogTitle from "@mui/material/DialogTitle";
import axios from "axios";

export default function CreateProjectForm() {
  const [open, setOpen] = React.useState(false);
  const [formValue, setformValue] = React.useState({
    projectName: "",
  });

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = (event) => {
    setOpen(false);
    handleCreate(formValue.projectName);
  };

  const handleCreate = (projectName) => {
    axios
      .post(
        "/exam-api/projects",
        {
          projectName: projectName,
        },
        {
          auth: {
            username: "admin",
            password: "password",
          },
        }
      )
      .then(function (response) {
        console.log(response);
      })
      .catch(function (error) {
        console.log(error);
      });
    setOpen(false);
  };

  const setTextValue = (event) => {
    setformValue({ [event.target.name]: event.target.value });
  };

  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        Create Project
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Create Project</DialogTitle>
        <DialogContent>
          <DialogContentText>This will create project.</DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="projectName"
            name="projectName"
            label="Project Name"
            type="text"
            fullWidth
            variant="standard"
            onChange={setTextValue}
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>Cancel</Button>
          <Button onClick={handleClose}>Create Project</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
