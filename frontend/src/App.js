import { Layout, Dropdown, Menu, Button } from "antd";
import { UserOutlined } from "@ant-design/icons";
import React from "react";
import LoginPage from "./components/LoginPage";
import HostHomePage from "./components/HostHomePage";
import GuestHomePage from "./components/GuestHomePage";


const { Header, Content } = Layout;


class App extends React.Component {
  state = {
    authed: false,
    asHost: false,
  };


  componentDidMount() {
    const authToken = localStorage.getItem("authToken");
    const asHost = localStorage.getItem("asHost") === "true";
    this.setState({
      authed: authToken !== null,
      asHost,
    });
  }


  handleLoginSuccess = (token, asHost) => {
    localStorage.setItem("authToken", token);
    localStorage.setItem("asHost", asHost);
    this.setState({
      authed: true,
      asHost,
    });
  };


  handleLogOut = () => {
    localStorage.removeItem("authToken");
    localStorage.removeItem("asHost");
    this.setState({
      authed: false,
    });
  };


  renderContent = () => {
    if (!this.state.authed) {
      return <LoginPage handleLoginSuccess={this.handleLoginSuccess} />;
    }


    if (this.state.asHost) {
      return <HostHomePage />;
    }


    return <GuestHomePage />;
  };


  userMenu = (
    <Menu>
      <Menu.Item key="logout" onClick={this.handleLogOut}>
        Log out
      </Menu.Item>
    </Menu>
  );

  render() {
    return (
      <Layout>
        <Header style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
          <div style={{ color: "white", fontSize: 20, fontWeight: 600 }}>Staybooking</div>
          {this.state.authed && (
            <Dropdown overlay={this.userMenu} trigger={["click"]}>
              <Button icon={<UserOutlined />}>Account</Button>
            </Dropdown>
          )}
        </Header>
        <Content style={{ padding: 24 }}>{this.renderContent()}</Content>
      </Layout>
    );
  }
}

export default App;
