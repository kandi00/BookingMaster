using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace ApiHeroku.Migrations
{
    public partial class InitialCreate4 : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "UserExample",
                columns: table => new
                {
                    UserId = table.Column<int>(type: "integer", nullable: false),
                    DisplayName = table.Column<string>(type: "character varying(60)", unicode: false, maxLength: 60, nullable: true),
                    UserName = table.Column<string>(type: "character varying(30)", unicode: false, maxLength: 30, nullable: true),
                    Email = table.Column<string>(type: "character varying(50)", unicode: false, maxLength: 50, nullable: true),
                    Password = table.Column<string>(type: "character varying(20)", unicode: false, maxLength: 20, nullable: true),
                    CreatedDate = table.Column<DateTime>(type: "timestamp with time zone", unicode: false, nullable: true)
                },
                constraints: table =>
                {
                });
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "UserExample");
        }
    }
}
